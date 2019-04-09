package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 产品及原料设置实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_product")
public class Product {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String density;//密度
	
	@Column(length=50)
	private String sole;//唯一值
	
	@ManyToOne
	@JoinColumn(name="typeId")
	private ProductType type;

	public ProductType getType() {
		return type;
	}

	public void setType(ProductType type) {
		this.type = type;
	}

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

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public String getSole() {
		return sole;
	}

	public void setSole(String sole) {
		this.sole = sole;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", density=" + density + ", sole=" + sole + "]";
	}
	
}
