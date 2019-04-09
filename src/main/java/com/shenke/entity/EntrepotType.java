package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 仓位type实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_entrepottype")
public class EntrepotType {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String icon;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private Integer pId;
	
	private Integer state;

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
		return "EntrepotType [id=" + id + ", icon=" + icon + ", name=" + name + ", pId=" + pId + ", state=" + state
				+ "]";
	}
	
}
