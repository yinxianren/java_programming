package org.yinxianren.com.netty.netty_simple;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.CharsetUtil;
import org.yinxianren.com.netty.tools.Println;

public class NettyServerChannelInitializer extends ChannelInitializer<SocketChannel> implements Println {

    //9. 往Pipeline链中添加自定义的handler类
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        sc.pipeline().addLast(new NettyServerHandler());//6.往Pipeline链中添加自定义的handler
    }


    //服务器端的业务处理类
    private class NettyServerHandler extends ChannelInboundHandlerAdapter{

        //读取数据事件
        public void channelRead(ChannelHandlerContext ctx,Object msg){
            println("Server:"+ctx);
            ByteBuf buf=(ByteBuf) msg;
            println("客户端发来的消息："+buf.toString(CharsetUtil.UTF_8));
        }

        //数据读取完毕事件
        public void channelReadComplete(ChannelHandlerContext ctx){
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello client(>^ω^<)喵",CharsetUtil.UTF_8));
        }

        //异常发生事件
        public void exceptionCaught(ChannelHandlerContext ctx,Throwable t){
            ctx.close();
        }
    }

}
