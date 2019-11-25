package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;

@Data
@TableName("agent_merchant_info")
public class AgentMerchantInfo  implements Serializable {
           private String agentMerchantId;// varchar(32) NOT NULL,
           private String agentMerchantName;// varchar(50) DEFAULT NULL COMMENT '代理商名称',
           private String agentMerchantShortName;// varchar(20) DEFAULT NULL COMMENT '代理商简称',
           private Integer agentIdentityType;// int(2) DEFAULT NULL COMMENT ' 证件类型',
           private String agentIdentityNum;// varchar(20) DEFAULT NULL COMMENT '证件号码',
           private String agentIdentityUrl;// varchar(100) DEFAULT NULL COMMENT '图片地址',
           private String agentPhone;// varchar(16) DEFAULT NULL COMMENT '电话',
           private Integer agentPhoneStatus;// int(2) DEFAULT NULL COMMENT '0：已经认证(新增时候默认未认证) ,1:未认证 ',
           private String agentEmail;// varchar(30) DEFAULT NULL COMMENT '邮箱',
           private Integer agentEmailStatus;// int(2) DEFAULT NULL COMMENT '0：已经认证(新增时候默认未认证) ,1:未认证 ',
           private String agentQq;// varchar(12) DEFAULT NULL,
           private Integer agentStatus;// int(2) DEFAULT NULL COMMENT '商户审核状态 0：启用 ,1:禁用，2：未审核',
}
