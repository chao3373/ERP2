package com.shenke.entity;

public class Test {
	
	private String name;//名称
	
	private Double width;//宽
	
	private Double high;//厚度
	
	private Double length;//长度

	private String color;//颜色
	
	private Integer num;//件数

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Test [name=" + name + ", width=" + width + ", high=" + high + ", length=" + length + ", color=" + color
				+ ", num=" + num + "]";
	}
	
}
