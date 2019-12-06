package org.yinxianren.com.netty.nio;

import org.junit.Test;
import org.yinxianren.com.netty.tools.Println;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer implements Println {

    @Test
    public void test() throws IOException {
        //创建一个 ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口号
        InetSocketAddress socketAddress = new InetSocketAddress(6666);
        serverSocketChannel.socket().bind(socketAddress);
        //设置非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //创建一个select
        Selector selector = Selector.open();
        //ServerSocketChannel 注册到 Selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true){
            if(selector.select(1000) == 0 ){//无客户端连接
                println("阻塞1S后，无客户端连接");
                continue;
            }
            //获取关注事件的集合
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeySet.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();

                //1.连接事件
                //关注  OP_ACCEPT 事件
                //   Tests whether this key's channel is ready to accept a new socket connection.
                if(key.isAcceptable()){
                    //为该客户端创建一个通道
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                   //set SocketChannel not block
                    socketChannel.configureBlocking(false);
                    // Registers this channel with the given selector, returning a selection key
                    socketChannel.register(selector,SelectionKey.OP_READ,byteBuffer);
                    //
                    println("客户端连接成功："+socketChannel.hashCode());
                }
                //2.读事件
                if(key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    // return该对象ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    socketChannel.read(buffer);
                    println("服务端："+new String(buffer.array()));
                }
                //手动移除selectKey,防止重复操作
                keyIterator.remove();
            }
        }
    }

}
