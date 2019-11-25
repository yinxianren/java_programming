package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.AnewPayOrderInfoMapper;
import org.yinxianren.springboot.service.PayOrderInfoDBService;
import org.yinxianren.springboot.table.PayOrderInfoTable;

@Service
public class PayOrderInfoDBServiceImpl extends ServiceImpl<AnewPayOrderInfoMapper, PayOrderInfoTable> implements PayOrderInfoDBService {
}
