package org.yinxianren.com.netty.group_chat;

import org.yinxianren.com.netty.tools.Println;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatSerivce implements Println {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static  final int PORT = 6667;

    public GroupChatSerivce() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);//设置非阻塞
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);//建通道注册到selector
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listen() {
        try{
            while (true){
                int count = selector.select(2000);
                if( !(count>0) ) {
//                   println("服务等待连接！");
                    continue;
                }
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //监听accept事件
                    if(selectionKey.isAcceptable())  accept();
                        //监听读事件
                    else if(selectionKey.isReadable()) read(selectionKey);

                    //手动移除selectKey,防止重复操作
                    iterator.remove();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public  void accept(){
        try {
            SocketChannel socketChannel = listenChannel.accept();
            //socketChannel 注册到selector
            socketChannel.configureBlocking(false);
            socketChannel.register(selector,SelectionKey.OP_READ);
            //提示
            println(String.format("%s 上线了",socketChannel.getRemoteAddress()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void read(SelectionKey selectionKey) {
        SocketChannel socketChannel = null;
        try{
            socketChannel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while ( (socketChannel.read(byteBuffer)) >0 ){
                String msg = new String(byteBuffer.array());
                println(String.format("服务端：%s",msg));
                //向其他客户端转发消息
                sendMsgToOtherClient(msg,socketChannel);
            }
        }catch (IOException e){
            try {
                println(String.format("%s 下线了",socketChannel.getRemoteAddress()));
                //取消注册
                selectionKey.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    void sendMsgToOtherClient(String msg,SocketChannel self){
        try {
            for (SelectionKey key : selector.keys()) {
                Channel channel = key.channel();
                //  排除自己，避免给自己发消息
                if (!(channel instanceof SocketChannel) || channel == self) continue;
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(wrap);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatSerivce groupChatSerivce = new GroupChatSerivce();
        groupChatSerivce.listen();
    }

}
