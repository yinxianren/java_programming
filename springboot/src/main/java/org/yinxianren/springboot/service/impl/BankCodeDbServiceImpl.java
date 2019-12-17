package org.yinxianren.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.yinxianren.springboot.mapper.AnewBankCodeMapper;
import org.yinxianren.springboot.service.BankCodeDbService;
import org.yinxianren.springboot.table.BankCodeTable;

/**
 * Created with IntelliJ IDEA.
 * User: panda
 * Date: 2019/11/1
 * Time: 下午3:51
 * Description:
 */
@Service
public class BankCodeDbServiceImpl extends ServiceImpl<AnewBankCodeMapper, BankCodeTable> implements BankCodeDbService {
}
