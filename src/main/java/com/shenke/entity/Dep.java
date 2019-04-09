package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部门实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_dep")
public class Dep {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=100)
	private String icon;//图标
	
	@Column(length=50)
	private String name;
	
	private Integer pId;//父菜单id
	
	private Integer state;//节点类型1根节点0叶子节点

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Dep [id=" + id + ", icon=" + icon + ", name=" + name + ", pId=" + pId + ", state=" + state + "]";
	}
	
}
