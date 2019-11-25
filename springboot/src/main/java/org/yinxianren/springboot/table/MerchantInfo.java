package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("merchant_info")
public class MerchantInfo {
    private String merId;// varchar(32) NOT NULL,
    private String merchantName;// varchar(50) DEFAULT NULL,
    private String merchantShortName;// varchar(20) DEFAULT NULL,
    private Integer type;// int(2) DEFAULT NULL,
    private String  parentId;// varchar(10) DEFAULT NULL,
    private String secretKey;// varchar(30) DEFAULT NULL,
    private Integer identityType;// int(2) DEFAULT NULL,
    private String identityNum;// varchar(20) DEFAULT NULL,
    private String identityUrl;// varchar(100) DEFAULT NULL,
    private String phone;// varchar(16) DEFAULT NULL,
    private Integer phoneStatus;// int(2) DEFAULT NULL,
    private String email;// varchar(30) DEFAULT NULL,
    private Integer emailStatus;// int(2) DEFAULT NULL,
    private String qq;// varchar(12) DEFAULT NULL,
    private Integer status;// int(2) DEFAULT NULL,
    @TableField("agreement_startTime")
    private Data AgreementStartTime;// datetime DEFAULT NULL,
    @TableField("agreement_endTime")
    private Data AgreementEndTime;// datetime DEFAULT NULL,
    private Data createTime;// datetime DEFAULT NULL,
}
