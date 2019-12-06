package org.yinxianren.com.netty.nio;

import org.junit.Test;
import org.yinxianren.com.netty.tools.Println;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class TestServerSocketChannel implements Println {


    @Test
    public void test() throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7070);
        //启动socket 并绑定端口
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建byteBuffer
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //获取连接
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;

        while(true){

            int byteRead = 0;
            while( byteRead < messageLength ){
                long length = socketChannel.read(byteBuffers);
                byteRead += length ;
                println("byteRead =" + byteRead);
                Arrays.asList(byteBuffers)
                        .stream()
                        .map(buffer ->"position = "+buffer.position()+",limit = "+buffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buf ->buf.flip());
            long byteWrite = 0;
            while( byteWrite < messageLength ){
               long length = socketChannel.write(byteBuffers);
                byteWrite += length ;
            }

            Arrays.asList(byteBuffers).forEach(buf ->buf.clear());
            println("byteRead ="+byteRead +",byteWrite ="+byteWrite);
        }


    }


    @Test
    public void test_01(){
        println(1 << 1);
        println(1 << 2);
        println(1 << 3);
        println(1 << 4);
        println(1 << 5);
        println(1 << 6);
    }

}
