package org.yinxianren.springboot.test.travel;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.yinxianren.springboot.tools.Println;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test01 implements Println {


    @Test
    public void test_01(){
        StringBuffer json = new StringBuffer();
        try{
            String address = "北京市";
            URL u = new URL("http://restapi.amap.com/v3/geocode/geo?address="+address+"&output=JSON&key=7f4ffae4074e8b8e4d147190527a4b72");
            URLConnection yc = u.openConnection();
            //读取返回的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8"));
            String inputline = null;
            while((inputline=in.readLine())!=null){
                json.append(inputline);
            }
            in.close();
            String jsonStr=json.toString();
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            if(jsonObject.getJSONArray("geocodes").size()>0)
                println( jsonObject.getJSONArray("geocodes").getJSONObject(0).get("location").toString());
            else
                println("null");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
