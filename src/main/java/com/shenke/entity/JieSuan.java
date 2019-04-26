package com.shenke.entity;

import java.io.Serializable;

public class JieSuan implements Serializable{
	private String name;//产品
	
	private Integer count;//数量

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public JieSuan(Object name, Object count) {
		super();
		this.name = (String) name;
		this.count = Integer.parseInt(count.toString());
	}

	@Override
	public String toString() {
		return "JieSuan [name=" + name + ", count=" + count + "]";
	}
	
}
