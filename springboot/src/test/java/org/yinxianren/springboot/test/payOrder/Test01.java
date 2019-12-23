package org.yinxianren.springboot.test.payOrder;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.yinxianren.springboot.service.PayOrderService;
import org.yinxianren.springboot.tools.PayMap;
import org.yinxianren.springboot.tools.Println;

import javax.annotation.Resource;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test01 implements Println {

    @Resource
    private PayOrderService payOrderService;


    @Test
    public void test01(){
        Map<String, Object> map = payOrderService.sumAmount(
                new PayMap<String,Object>()
                .lput("merId","M201004")
        );
        println(JSON.toJSON(map));
    }


}
