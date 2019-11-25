package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.AgentMerchantInfoMapper;
import org.yinxianren.springboot.service.AgentMerchantInfoService;
import org.yinxianren.springboot.table.AgentMerchantInfo;

@Service
public class AgentMerchantInfoServiceImpl extends ServiceImpl<AgentMerchantInfoMapper, AgentMerchantInfo> implements AgentMerchantInfoService {
}
