package org.yinxianren.com.netty.one;

import org.yinxianren.com.netty.tools.Println;
import org.yinxianren.com.netty.tools.ThreadPoolExecutorComponent;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer implements Println {

    private ServerSocket serverSocket = null;
    private BIOServer(){ }

    public BIOServer(int serverPort){
        try {
            serverSocket = this.init(serverPort);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private ServerSocket init(int serverPort) throws IOException {
        println(String.format("初始化了serverSocket,端口号为：%s",serverPort));
        return new ServerSocket(serverPort);
    }

    public ServerSocket connection(){
        return this.serverSocket;
    }

    public void start(ServerSocket serverSocket){
        try {
            while (true){
                final Socket socket = serverSocket.accept();
                println(String.format("有新客户端(%s---%s)连接上了",Thread.currentThread().getName(),socket.getInetAddress()));
                ThreadPoolExecutorComponent
                        .executorService.execute(()->handle(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handle( Socket socket){
        try{
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            int count = 0;
            while( -1 != (count=inputStream.read(bytes)) ){
                println(String.format("(%s)服务端<-<-<-收到信息来自客户端（%s）::%s",Thread.currentThread().getName(),socket.getInetAddress(),new String(bytes,0,count)));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                println(String.format("(%s)客户端（%s）断开连接了",Thread.currentThread().getName(),socket.getInetAddress()));
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
