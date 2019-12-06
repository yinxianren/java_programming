package org.yinxianren.com.netty.nio;

import org.junit.Test;
import org.yinxianren.com.netty.tools.Println;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient implements Println {

    @Test
    public void test() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        boolean connect = socketChannel.connect(inetSocketAddress);
        if( !connect ){
            while ( !socketChannel.finishConnect() ){
                println("还未连接成功，可以做其他事情了");
                println("............................");
            }
        }
        //连接成功要处理的事情
        //发生数据
        String str = "Hello world,I am  FiChan";
        //Wraps a byte array into a buffer.
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //发送数据
        socketChannel.write(byteBuffer);
        System.in.read();
    }


}
