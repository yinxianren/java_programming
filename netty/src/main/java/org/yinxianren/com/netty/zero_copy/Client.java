package org.yinxianren.com.netty.zero_copy;

import org.yinxianren.com.netty.tools.Println;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class Client implements Println {


    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 7001));
        String fileName = "";
        FileChannel fileChannel = new FileInputStream(new File(fileName)).getChannel();
        long stardTime = System.currentTimeMillis();
        /*
         在linux系统下调用transferTo方法，可以完成传输
         在windows,调用transferTo 只能发生8M，就需要分段传输文件
         */
        long transferToLength = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
         System.out.println(String.format("总共传送了:%s，耗时：%s",
                 transferToLength,System.currentTimeMillis()-stardTime));
        fileChannel.close();
        socketChannel.close();
    }
}
