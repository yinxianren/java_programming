package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.MerchantInfoMapper;
import org.yinxianren.springboot.service.MerchantInfoService;
import org.yinxianren.springboot.table.MerchantInfo;

@Service
public class MerchantInfoServiceImpl extends ServiceImpl<MerchantInfoMapper, MerchantInfo> implements MerchantInfoService {
}
