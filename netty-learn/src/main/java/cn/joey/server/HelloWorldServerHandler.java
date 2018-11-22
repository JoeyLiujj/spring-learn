package cn.joey.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author liujiji
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("server channelRead...");
        System.out.println(ctx.channel().remoteAddress()+"->ChatServer："+msg.toString());
        ctx.write("server write:"+msg);
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
