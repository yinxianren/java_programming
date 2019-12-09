package org.yinxianren.com.netty.group_chat;

import org.yinxianren.com.netty.tools.Println;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

public class GroupChatClient implements Println {
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;


    public GroupChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            userName = socketChannel.getLocalAddress().toString().substring(1);// //得到用户名
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     *   向服务器发送消息
     * @param info
     */
    void sendInfo(String info){
        info = userName + "说：" + info;
        try{
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    void readInfo(){
        try{
            int readChannels = selector.select();
            if( readChannels <1 ) return;
            Iterator<SelectionKey> iterator = selector.keys().iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isReadable()) read(selectionKey);
                iterator.remove();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void read( SelectionKey selectionKey) {
        try {
            SocketChannel channel = (SocketChannel) selectionKey.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            channel.read(byteBuffer);
            String msg = new String(byteBuffer.array());
            println(msg.trim());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        GroupChatClient chatClient = new GroupChatClient();
        new Thread(()->{
            while (true) {
                chatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            chatClient.sendInfo(line);
        }

    }

}
