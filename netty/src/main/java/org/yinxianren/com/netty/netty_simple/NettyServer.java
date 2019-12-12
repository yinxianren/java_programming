package org.yinxianren.com.netty.netty_simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.NettyRuntime;
import org.junit.Test;
import org.yinxianren.com.netty.tools.Println;

public class NettyServer  implements Println {


    @Test
    public  void  test() throws InterruptedException {
        //1. 创建一个线程组：接收客户端连接
        EventLoopGroup bossGroup =new NioEventLoopGroup(1);
        //2. 创建一个线程组：处理网络操作
        EventLoopGroup workerGroup =new NioEventLoopGroup(16);
        //3. 创建服务器端启动助手来配置参数
        ServerBootstrap b=new ServerBootstrap();
        //4.设置两个线程组
        b.group(bossGroup,workerGroup)
                //5.使用NioServerSocketChannel作为服务器端通道的实现
                .channel(NioServerSocketChannel.class)
                //6.设置线程队列中等待连接的个数
                .option(ChannelOption.SO_BACKLOG,128)
                //7.保持活动连接状态
                .childOption(ChannelOption.SO_KEEPALIVE,true)
                //8. 创建一个通道初始化对象
                .childHandler(new NettyServerChannelInitializer());

        println("......Server is ready......");
        ChannelFuture cf=b.bind(6668).sync();  //10. 绑定端口 bind方法是异步的  sync方法是同步阻塞的
        println("......Server is starting......");

        //11. 关闭通道，关闭线程组
        cf.channel().closeFuture().sync(); //异步
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }


    @Test
    public void test_01(){
        println(String.format("我的电脑有多少核：%s"
                , NettyRuntime.availableProcessors()));
    }

}
