package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 客户实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_client")
public class Client {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=50)
	private String help;
	
	@Column(length=50)
	private String address;
	
	@Column(length=50)
	private String tel;
	
	@Column(length=50)
	private String mobile;
	
	@Column(length=50)
	private String linkman;
	
	@Column(length=500)
	private String remark;
	
	@ManyToOne
	@JoinColumn(name="typeId")
	private ClientType type;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ClientType getType() {
		return type;
	}

	public void setType(ClientType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", help=" + help + ", address=" + address + ", tel=" + tel
				+ ", mobile=" + mobile + ", linkman=" + linkman + ", remark=" + remark + ", type=" + type + "]";
	}
	
}
