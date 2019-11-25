package org.yinxianren.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.yinxianren.springboot.table.PayOrder;

import java.util.Set;

public interface PayOrderMapper extends BaseMapper<PayOrder> {

    Set<String> getTimeGroup();
}
