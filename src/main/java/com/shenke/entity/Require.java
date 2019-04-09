package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 要求设置实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_require")
public class Require {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String name;//要求名称
	
	@Column(length=50)
	private String help;//助记词
	
	@Column(length=50)
	private String remark;//备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHelp() {
		return help;
	}

	public void setHelp(String help) {
		this.help = help;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Require [id=" + id + ", name=" + name + ", help=" + help + ", remark=" + remark + "]";
	}
	
}
