package org.yinxianren.springboot.table;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("organization_info")
public class OrganizationInfo {
    private String organizationId;// varchar(32) NOT NULL,
    private String organizationName;// varchar(32) DEFAULT NULL,
    private Integer status;// int(6) DEFAULT NULL,
    private Date createTime;// datetime DEFAULT NULL,
    private String creator;// varchar(32) DEFAULT NULL,
    private String remark;// varchar(64) DEFAULT NULL,
}
