package org.yinxianren.mybatis.one;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AsyncNotifyTable {

    private Long id;// bigint(19) NOT NULL AUTO_INCREMENT COMMENT 表主键
    private String merchantId;// varchar(32) NOT NULL COMMENT 商户号
    private String terminalMerId;// varchar(64) NOT NULL COMMENT 终端商户号
    private String merOrderId;// varchar(64) NOT NULL COMMENT 商户订单号
    private String platformOrderId;// varchar(64) NOT NULL COMMENT 平台订单号
    private BigDecimal amount;// decimal(122) DEFAULT 0.00 COMMENT 订单金额
    private Integer orderStatus;// tinyint(2) DEFAULT 0 COMMENT 订单状态（0成功、1失败、2未处理、3处理中）
    private Integer notifyCount;// tinyint(2) DEFAULT 0 COMMENT 通知次数
    private Integer status;// tinyint(2) DEFAULT 0 COMMENT 状态 0：结束通知1:继续通知
    private String notifyUrl;// varchar(128) NOT NULL COMMENT 通知地址
    private String respResult;// varchar(1024) DEFAULT NULL COMMENT 客户响应结果
    private Date createTime;// datetime NOT NULL COMMENT 创建时间
    private Date updateTime;// datetime NOT NULL COMMENT 更新时间
}
