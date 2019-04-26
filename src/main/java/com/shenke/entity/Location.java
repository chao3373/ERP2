package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 仓位设置实体类
 * 
 * @author shao
 *
 */
@Entity
@Table(name="t_location")
public class Location {
	@Id
	@GeneratedValue
	private Integer id; //id
	
	@Column(length=50)
	private  String name;

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

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + "]";
	}
	
	
}
