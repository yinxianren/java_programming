package org.yinxianren.com.netty;

import org.junit.Test;
import org.yinxianren.com.netty.one.BIOServer;

import java.net.ServerSocket;

public class TestOne {

    @Test
    public void test_1(){
       BIOServer bioServer = new BIOServer(8945);
       ServerSocket serverSocket = bioServer.connection();
       bioServer.start(serverSocket);
    }

}
