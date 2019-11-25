package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("pay_order")
public class PayOrder implements Serializable {
    private String payId ;
    private String merId ;
    private String merOrderId;
    private String organizationId ;
    private String channelId;
    private String payType;
    private String channelTransCode;
    private String currency;
    private BigDecimal amount ;
    private BigDecimal realAmount;
    private BigDecimal terminalFee ;
//    private BigDecimal payFee;
    private BigDecimal fee;
    private BigDecimal channelFee;
    private BigDecimal agentFee;
    private BigDecimal income;
    private String settleCycle;
    private Integer orderStatus;
    private Integer settleStatus;
    private Date tradeTime;
    private Date bankTime;
    private String orgOrderId;
    private String tradeResult;
    private String terminalMerId;
    private String terminalMerName;
    private String agmentId;
    private String bankCardNum;

}
