package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("channel_info")
public class ChannelInfo {

    private String channelId;// varchar(20) NOT NULL,
    private String organizationId;// varchar(32) DEFAULT NULL,
    private String channelName;// varchar(64) DEFAULT NULL,
    private Integer channelLevel;// int(4) DEFAULT NULL,
    private Integer type;// int(2) DEFAULT NULL,
    private String channelTransCode;// varchar(20) DEFAULT NULL,
    private String payUrl;// varchar(64) DEFAULT NULL,
    private String others;// longtext,
    private BigDecimal channelSingleFee;// decimal(6,2) DEFAULT NULL,
    private BigDecimal  channelRateFee;// decimal(6,2) DEFAULT NULL,
    private String settleCycle;// varchar(10) DEFAULT NULL,
    private BigDecimal singleMinAmount;// decimal(10,2) DEFAULT NULL,
    private BigDecimal singleMaxAmount;// decimal(10,2) DEFAULT NULL,
    private BigDecimal dayQuotaAmount;// decimal(10,2) DEFAULT NULL,
    private BigDecimal monthQuotaAmount;// decimal(10,2) DEFAULT NULL,
    private String  outChannelId;// varchar(20) DEFAULT NULL,
    private Integer status;// int(2) DEFAULT NULL,
    private Date createTime;// date DEFAULT NULL,
}
