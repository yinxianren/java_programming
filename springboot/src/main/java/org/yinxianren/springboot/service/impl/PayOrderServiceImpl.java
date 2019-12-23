package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.PayOrderMapper;
import org.yinxianren.springboot.service.PayOrderService;
import org.yinxianren.springboot.table.PayOrder;

import java.util.Map;

@Service
public class PayOrderServiceImpl extends ServiceImpl<PayOrderMapper, PayOrder> implements PayOrderService {

    @Override
    public Map<String, Object> sumAmount(Map<String, Object> map) {
        return  baseMapper.sumAmount(map);
    }
}
