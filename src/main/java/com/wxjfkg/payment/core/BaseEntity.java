package com.wxjfkg.payment.core;

import java.io.Serializable;
import java.util.Date;

public class BaseEntity<PK extends Serializable> implements Serializable {

	private PK id;

	private Date createTime;

	private Date editTime;

	private Long version;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

}
