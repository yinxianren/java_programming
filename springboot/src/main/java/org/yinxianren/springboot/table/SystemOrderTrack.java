package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("system_order_track")
public class SystemOrderTrack {
    private String  id;// varchar(32) NOT NULL,
    private String merId;// varchar(10) DEFAULT NULL,
    private String merOrderId;// varchar(32) DEFAULT NULL,
    private String orderId;// varchar(32) DEFAULT NULL,
    private BigDecimal amount;// decimal(10,2) DEFAULT NULL,
    private String tradeInfo;// text,
    private String  returnUrl;// varchar(128) DEFAULT NULL,
    private String noticeUrl;// varchar(128) DEFAULT NULL,
    private String refer;// varchar(128) DEFAULT NULL,
    private Integer orderTrackStatus;// int(2) DEFAULT NULL,
    private String tradeResult;// varchar(500) DEFAULT NULL,
    private Date tradeTime;// datetime DEFAULT NULL,
}
