package org.yinxianren.com.netty.nio;

import org.junit.Test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MappedByBuffer {


    @Test
    public void test() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("E:\\data\\logs\\spring2.log","rw");
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数1：读写模式
         * 参数2：可以直接修改的起始位置
         * 参数3： 映射到内存大学，即将文件的多少字节映射到内存，可以直接修改的范围
         */
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 25);

        for(int i=0;i<25;i++){
            map.put(i,(byte) '5'); //修改内容
        }
        channel.close();
        randomAccessFile.close();
    }

}
