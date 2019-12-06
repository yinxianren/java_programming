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

    public MerchantInfo setMerId(String merId) {
        this.merId = merId;
        return this;
    }

    public MerchantInfo setMerchantName(String merchantName) {
        this.merchantName = merchantName;
        return this;
    }

    public MerchantInfo setMerchantShortName(String merchantShortName) {
        this.merchantShortName = merchantShortName;
        return this;
    }

    public MerchantInfo setType(Integer type) {
        this.type = type;
        return this;
    }

    public MerchantInfo setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public MerchantInfo setSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public MerchantInfo setIdentityType(Integer identityType) {
        this.identityType = identityType;
        return this;
    }

    public MerchantInfo setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
        return this;
    }

    public MerchantInfo setIdentityUrl(String identityUrl) {
        this.identityUrl = identityUrl;
        return this;
    }

    public MerchantInfo setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public MerchantInfo setPhoneStatus(Integer phoneStatus) {
        this.phoneStatus = phoneStatus;
        return this;
    }

    public MerchantInfo setEmail(String email) {
        this.email = email;
        return this;
    }

    public MerchantInfo setEmailStatus(Integer emailStatus) {
        this.emailStatus = emailStatus;
        return this;
    }

    public MerchantInfo setQq(String qq) {
        this.qq = qq;
        return this;
    }

    public MerchantInfo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public MerchantInfo setAgreementStartTime(Data agreementStartTime) {
        AgreementStartTime = agreementStartTime;
        return this;
    }

    public MerchantInfo setAgreementEndTime(Data agreementEndTime) {
        AgreementEndTime = agreementEndTime;
        return this;
    }

    public MerchantInfo setCreateTime(Data createTime) {
        this.createTime = createTime;
        return this;
    }
}
