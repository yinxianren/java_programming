package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.OrganizationInfoMapper;
import org.yinxianren.springboot.service.OrganizationInfoService;
import org.yinxianren.springboot.table.OrganizationInfo;

@Service
public class OrganizationInfoServiceImpl extends ServiceImpl<OrganizationInfoMapper, OrganizationInfo> implements OrganizationInfoService {
}
