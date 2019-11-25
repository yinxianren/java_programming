package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.MerchantRateMapper;
import org.yinxianren.springboot.service.MerchantRateService;
import org.yinxianren.springboot.table.MerchantRate;

@Service
public class MerchantRateServiceImpl extends ServiceImpl<MerchantRateMapper, MerchantRate> implements MerchantRateService {
}
