package org.yinxianren.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.yinxianren.springboot.table.PayOrder;

import java.util.Map;

public interface PayOrderService extends IService<PayOrder> {

    Map<String,Object> sumAmount(Map<String,Object> map);

}
