package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("merchant_rate")
public class MerchantRate {
    private String id;// varchar(32) NOT NULL,
    private String merId;// varchar(10) DEFAULT NULL,
    private String payType;// varchar(20) DEFAULT NULL,
    private BigDecimal singleFee;// decimal(6,2) DEFAULT NULL,
    private BigDecimal rateFee;// decimal(6,2) DEFAULT NULL,
    private BigDecimal bondRate;// decimal(6,2) DEFAULT NULL,
    private Integer bondCycle;// int(3) DEFAULT NULL,
    private String settlecycle;// varchar(10) DEFAULT NULL,
    private Integer status;// int(2) DEFAULT NULL,
}
