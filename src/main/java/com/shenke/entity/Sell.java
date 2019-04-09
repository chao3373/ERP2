package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 销售方式实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_sell")
public class Sell {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=20)
	private String method;
	
	@Column(length=500)
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Sell [id=" + id + ", method=" + method + ", remark=" + remark + "]";
	}
	
}
