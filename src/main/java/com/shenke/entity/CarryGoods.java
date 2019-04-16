package com.shenke.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 提货单实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_carryGoods")
public class CarryGoods {
	@Id
	@GeneratedValue
	private Long id;

	private String clientName;// 客户名称

	private String productName;// 产品名称

	private String color;// 颜色

	private Double model;// 幅宽

	private Double weight;// 克重

	private Double price;// 厚度

	private Double length;// 长度

	private Double squareMeter;// 平米

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getModel() {
		return model;
	}

	public void setModel(Double model) {
		this.model = model;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getSquareMeter() {
		return squareMeter;
	}

	public void setSquareMeter(Double squareMeter) {
		this.squareMeter = squareMeter;
	}

	@Override
	public String toString() {
		return "CarryGoods [id=" + id + ", clientName=" + clientName + ", productName=" + productName + ", color="
				+ color + ", model=" + model + ", weight=" + weight + ", price=" + price + ", length=" + length
				+ ", squareMeter=" + squareMeter + "]";
	}

}
