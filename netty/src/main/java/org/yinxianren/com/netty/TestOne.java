package org.yinxianren.com.netty;

import com.sun.istack.internal.NotNull;
import org.junit.Test;
import org.yinxianren.com.netty.one.BIOServer;
import org.yinxianren.com.netty.tools.Println;

import java.net.ServerSocket;
import java.util.Spliterator;

public class TestOne implements Println {

    @Test
    public void test_1(){
       BIOServer bioServer = new BIOServer(8945);
       ServerSocket serverSocket = bioServer.connection();
       bioServer.start(serverSocket);
    }


    @Test
    public void test_2(){
        test_021(null);
    }




    private String test_021(@NotNull String name){
        println(name);
        return name;
    }
}
