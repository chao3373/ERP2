package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 盘点机实体类
 * 
 * @author shao
 *
 */

@Entity
@Table(name="t_pandianji")
public class Pandianji {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=255)
	private String name;
	
	@Column(length=255)
	private String pid;
	
	@Column(length=500)
	private String remark;

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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Pandianji [id=" + id + ", name=" + name + ", pid=" + pid + ", remark=" + remark + "]";
	}
	
	
}
