package org.yinxianren.mybatis.one;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class OneDay implements Println{

    @Test
    public void test_01() throws  Exception{
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AsyncNotifyTable asyncNotifyTable = (AsyncNotifyTable) session.selectOne("org.yinxianren.mybatis.one.AsyncNotifyMapper.selectOne", 1);
            println(asyncNotifyTable);
        }

    }


// AsyncNotifyTable


    @Test
    public void test_02() throws IOException {
        URL url = new URL("https://mybatis.org/mybatis-3/zh/getting-started.html");
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bytes = new byte[1024];
        while ( -1 != bufferedInputStream.read(bytes) ){
            println(new String(bytes));
        }

    }

}
