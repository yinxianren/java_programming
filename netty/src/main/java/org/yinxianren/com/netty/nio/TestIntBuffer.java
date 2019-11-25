package org.yinxianren.com.netty.nio;

import org.junit.Test;
import org.yinxianren.com.netty.tools.Println;

import java.nio.IntBuffer;

public class TestIntBuffer implements Println {

    @Test
    public void test_01(){
        //初始化
        IntBuffer  intBuffer = IntBuffer.allocate(15);
        //赋值
        for(int i=0;i<intBuffer.capacity();i++){
            intBuffer.put(i*15);
        }
        //读写切换
        intBuffer.flip();
        //读数据
        while(intBuffer.hasRemaining()){
            println(intBuffer.get());
        }

    }

}
