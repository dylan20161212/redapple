package com.thtf.deconfliction.domain;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "用户处理过的案件统计视图")
@Entity
@Table(name = "v_user_processed_cases_number")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VUserProcessedCasesNumber implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Id
	 */
	@Id
	private String userId;

	/**
	 * 案件数量
	 */
	@ApiModelProperty(value = "用户名")
	private String userName;

	/**
	 * 案件数量
	 */
	@ApiModelProperty(value = "案件数量")
	private String count;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}
