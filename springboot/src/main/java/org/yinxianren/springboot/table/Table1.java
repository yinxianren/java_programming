package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("table1")
public class Table1 {
    private Long id;// bigint(19) NOT NULL AUTO_INCREMENT
    private String name;// varchar(50) NOT NULL COMMENT '常量组名称'
    private String code;// varchar(100) NOT NULL COMMENT '常量组编码'
    private Integer model;// smallint(1) NOT NULL COMMENT '1:系统 2:商户 3:代理商'
    private Integer system;// smallint(11) DEFAULT NULL COMMENT '0: 否 1: 是'
    private Date createTime;// datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
    private Date updateTime;// datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
    private Integer status;// tinyint(1) DEFAULT '0' COMMENT '启用状态 0启用，1禁用'
}
