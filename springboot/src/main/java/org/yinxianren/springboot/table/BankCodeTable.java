package org.yinxianren.springboot.table;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author  monkey
 * @Date 2019-10-31 
 */

@TableName ( "1_bank_code_table" )
@Data
public class BankCodeTable implements Serializable {
	@TableId(type= IdType.AUTO)
	private Long id;//表主键
	private String bankCode;//银行编码
	private String bankShortName;//银行名简称
	private String bankFullName;//银行名全称
	private Integer status;//0:可用，1：不可用
	private String remark;//备注
	private Date updateTime;//更新时间
	private Date createTime;//创建时间

	public BankCodeTable setId(Long id) {
		this.id = id;
		return this;
	}

	public BankCodeTable setBankCode(String bankCode) {
		this.bankCode = bankCode;
		return this;
	}

	public BankCodeTable setBankShortName(String bankShortName) {
		this.bankShortName = bankShortName;
		return this;
	}

	public BankCodeTable setBankFullName(String bankFullName) {
		this.bankFullName = bankFullName;
		return this;
	}

	public BankCodeTable setStatus(Integer status) {
		this.status = status;
		return this;
	}

	public BankCodeTable setRemark(String remark) {
		this.remark = remark;
		return this;
	}

	public BankCodeTable setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public BankCodeTable setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}
}
