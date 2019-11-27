package org.yinxianren.springboot.test.allinpay;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.util.StringUtils;
import org.yinxianren.springboot.tools.HttpClientUtils;
import org.yinxianren.springboot.tools.PayTreeMap;
import org.yinxianren.springboot.tools.Println;
import org.yinxianren.springboot.tools.UUID;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Set;

public class QueryBalance implements Println{



    @Test
    public void  query(){
        PayTreeMap<String,Object> treeMap = new PayTreeMap<String,Object>()
                .lput("orgid","")//机构号	平台分配的机构号	否
                .lput("appid","")//应用ID	平台分配的机构APPID	否
                .lput("randomstr", UUID.getUUID())
                .lput("cusid","")//商户号
                .lput("key","")
                ;

        treeMap.lput("sign",getMd5Sign(treeMap));
        treeMap.remove("key");

        StringBuilder sb = new StringBuilder();
        Set<String> keySet = treeMap.keySet();
        for(String key : keySet ){
            sb.append("\n"+key+ ":"+ treeMap.get(key)+"\n");
        }
        println("\n*********************************************************\n"
                +sb.toString()
                +"\n*********************************************************\n");

        String content = HttpClientUtils.doPost(HttpClientUtils.getHttpsClient(), "https://ipay.allinpay.com/apiweb/acct/balance", treeMap);
        Map<String,Object> map = JSON.parseObject(content,Map.class);
        sb.delete(0,sb.length());
        Set<String> resultKey = map.keySet();
        for(String key : resultKey ){
            sb.append("\n"+key+ ":"+ map.get(key)+"\n");
        }
        println("\n*********************************************************\n"
                +sb.toString()
                +"\n*********************************************************\n");
    }


    private  String getMd5Sign(Map<String,Object> postData){
        StringBuffer sb = new StringBuffer();
        for (String key : postData.keySet()){
            String value = postData.get(key) != null ? postData.get(key).toString() : null;
            if(!StringUtils.isEmpty(value)){
                sb.append(key+"="+value+"&");
            }
        }
        String sign = sb.substring(0,sb.lastIndexOf("&"));
        String md5Sign=  DigestUtils.md5Hex(sign.getBytes(StandardCharsets.UTF_8)).toUpperCase();
        return md5Sign;
    }

}
