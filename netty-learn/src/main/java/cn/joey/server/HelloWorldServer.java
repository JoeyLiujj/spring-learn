package cn.joey.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

/**
 * @author liujiji
 */
public class HelloWorldServer {
    private int port;

    public HelloWorldServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sbs = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                        .localAddress(new InetSocketAddress(port))
                    // ChannelInitializer是一个特殊的处理类，他的目的是帮助使用者配置一个新的channel
                    //也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的Channel
                    //或者其对应的ChannelPipeline来实现你的网络程序。当你的程序变的复杂时，可能你会增加更多的处理类到pipline上，
                    //然后提取这些匿名类到最顶层的类上。
                            .childHandler(
                                new ChannelInitializer<SocketChannel>() {
                                    @Override
                                    protected void initChannel(SocketChannel socketChannel) {
                                        socketChannel.pipeline().addLast("decoder", new StringDecoder());
                                        socketChannel.pipeline().addLast("encoder", new StringEncoder());
                                        socketChannel.pipeline().addLast(new HelloWorldServerHandler());
                                    }
                                }
                            ).option(ChannelOption.SO_BACKLOG, 30)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //绑定端口，开始接受进来的连接
            ChannelFuture future = sbs.bind(port).sync();
            System.out.println("Server start listen at " + port);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new HelloWorldServer(port).start();
    }
}
