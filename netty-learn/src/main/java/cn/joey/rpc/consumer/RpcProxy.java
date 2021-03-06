package cn.joey.rpc.consumer;

import cn.joey.rpc.consumer.proxy.RpcProxyHandler;
import cn.joey.rpc.protocol.InvokerProtocol;
import cn.joey.rpc.registry.RegistryHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @auther liujiji
 * @date 2019/6/18 16:10
 */
public class RpcProxy {
    public static <T> T create(Class<?> clazz){
        MethodProxy proxy = new MethodProxy(clazz);
        Class<?>[] interfaces = clazz.isInterface()?new Class[]{clazz}:clazz.getInterfaces();
        T result = (T) Proxy.newProxyInstance(clazz.getClassLoader(),interfaces,proxy);
        return result;
    }

    public static class MethodProxy implements InvocationHandler {

        private Class<?> clazz;

        public MethodProxy(Class<?> clazz){
            this.clazz = clazz;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //如果传过来的是一个具体的实现类(本次演示 略过此逻辑)
            if(Object.class.equals(method.getDeclaringClass())){
                try {
                    return method.invoke(this,args);
                } catch(Throwable a) {
                    a.printStackTrace();
                }
            }else{
                return rpcInvoke(proxy,method,args);
            }
            return null;
        }

        public Object rpcInvoke(Object proxy, Method method, Object[] args) {
            //传输协议封装
            InvokerProtocol msg = new InvokerProtocol();
            msg.setClassName(this.clazz.getName());
            msg.setMethodName(method.getName());
            msg.setValues(args);
            msg.setParames(method.getParameterTypes());

            final RpcProxyHandler consumerHandler = new RpcProxyHandler();
            EventLoopGroup group = new NioEventLoopGroup();

            try {
                Bootstrap b = new Bootstrap();
                b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel socketChannel) {
                                ChannelPipeline pipeline = socketChannel.pipeline();
                                //自定义协议解码器
                                /**
                                 * 入参有5个，分别解释如下
                                 * maxFrameLength: 框架的最大长度，如果帧的长度大于此值，则抛出TooLongFrameException
                                 * lengthFieldOffset:长度字段的偏移量：即对应的长度字段在整个消息数据中的位置
                                 * lengthFieldLength：长度字段的长度，如长度字段是int型表示，那么这个值就是4 long型是8
                                 * lengthAdjustment：要添加到长度字段值的补偿值
                                 * initialBytesToStrip：从解码帧中去除的第一个字节数
                                 */
                                pipeline.addLast("frameDecoder",new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                                //自定义协议解码器
                                pipeline.addLast("frameEncoder",new LengthFieldPrepender(4));
                                //自定义参数类型编码器
                                pipeline.addLast("encoder", new ObjectEncoder());
                                //自定义参数类型解码器
                                pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                                //
                                pipeline.addLast("handler",consumerHandler);

                            }
                        });
                ChannelFuture future = b.connect("localhost",8080).sync();
                future.channel().writeAndFlush(msg).sync();
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return consumerHandler.getResponse();
        }
    }
}
