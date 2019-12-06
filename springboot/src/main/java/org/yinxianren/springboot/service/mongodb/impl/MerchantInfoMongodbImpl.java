package org.yinxianren.springboot.service.mongodb.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.aop.annotation.MgdbGetOne;
import org.yinxianren.springboot.service.MerchantInfoService;
import org.yinxianren.springboot.service.mongodb.MerchantInfoMongodb;
import org.yinxianren.springboot.table.MerchantInfo;

@Service
public class MerchantInfoMongodbImpl implements MerchantInfoMongodb {

    @Autowired
    private MerchantInfoService merchantInfoService;

    @MgdbGetOne(collectionName = "merchantInfo")
    @Override
    public MerchantInfo getOne(MerchantInfo mi) {
        LambdaQueryWrapper<MerchantInfo> queryWrapper = new QueryWrapper<MerchantInfo>()
                .lambda().eq(MerchantInfo::getStatus,mi.getStatus());
        queryWrapper.eq(MerchantInfo::getMerId,mi.getMerId());
        return  merchantInfoService.getOne(queryWrapper);
    }


}
