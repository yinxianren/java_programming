package org.yinxianren.com.netty.netty_simple;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.yinxianren.com.netty.tools.Println;

public class NettyClientChannelInitializer extends ChannelInitializer<SocketChannel> implements Println {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new NettyClientHandler()); //6.往Pipeline链中添加自定义的handler
    }

    //客户端业务处理类
    private class NettyClientHandler extends ChannelInboundHandlerAdapter {

        //通道就绪事件
        public void channelActive(ChannelHandlerContext ctx){
            System.out.println("Client:"+ctx);
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello, server ^(*￣(oo)￣)^",CharsetUtil.UTF_8));
        }

        //读取数据事件
        public void channelRead(ChannelHandlerContext ctx,Object msg){
            ByteBuf buf=(ByteBuf) msg;
            println("服务器端发来的消息："+buf.toString(CharsetUtil.UTF_8));
        }

    }
}
