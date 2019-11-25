package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.SystemOrderTrackMapper;
import org.yinxianren.springboot.service.SystemOrderTrackService;
import org.yinxianren.springboot.table.SystemOrderTrack;

@Service
public class SystemOrderTrackServiceImpl  extends ServiceImpl<SystemOrderTrackMapper, SystemOrderTrack> implements SystemOrderTrackService {
}
