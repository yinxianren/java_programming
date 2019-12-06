package org.yinxianren.springboot.test.mongodb;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yinxianren.springboot.service.mongodb.MerchantInfoMongodb;
import org.yinxianren.springboot.table.MerchantInfo;
import org.yinxianren.springboot.tools.Println;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test implements Println {


    @Autowired
    private MerchantInfoMongodb merchantInfoMongodb;

    @org.junit.Test
    public void test_02(){
        long startTime = System.currentTimeMillis();
        MerchantInfo merchantInfo = merchantInfoMongodb.getOne(new MerchantInfo()
                .setMerId("M201010")
                .setStatus(0)
        );
        long endTime = System.currentTimeMillis();
        long expend =  endTime - startTime;
        println("消耗："+ expend);
        println(merchantInfo.toString());
    }
//查询mysql数据
/*
    消耗1：801
    消耗2：803
    消耗3：729

*/
//查询Mongodb
/*
   消耗1：434
   消耗2：284
   消耗3：354
 */


    @org.junit.Test
    public void test_01(){
//        MerchantInfoTable merchantInfoTable = new MerchantInfoTable()
//                .setId(2L)
//                .setMerchantId("M001002")
//                .setMerchantName("江小黑")
//                .setEmail("xiaohei@jian.com");
//        mongoTemplate.save(merchantInfoTable);
    }




}
