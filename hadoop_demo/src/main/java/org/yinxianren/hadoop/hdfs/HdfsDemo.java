package org.yinxianren.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;


public class HdfsDemo {



    /**
     * 查看文件内容
     *
     *   hdfs://192.168.1.111:9000
     */
    @Test
    public  void test() throws IOException {
        Path path = new Path("/demo/input/log.log");
        FileSystem fs = FileSystem.get(URI.create("hdfs://192.168.1.111:9000"), new Configuration());
        FSDataInputStream fsdis = null;
        try {
            fsdis = fs.open(path);
            IOUtils.copyBytes(fsdis, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(fsdis);
            fs.close();
        }
    }


}
