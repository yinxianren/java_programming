package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.ChannelInfoMapper;
import org.yinxianren.springboot.service.ChannelInfoService;
import org.yinxianren.springboot.table.ChannelInfo;

@Service
public class ChannelInfoServiceImpl extends ServiceImpl<ChannelInfoMapper, ChannelInfo> implements ChannelInfoService {
}
