package com.shenke.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 收付款方式实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_receipt")
public class Receipt {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(length=50)
	private String name;
	
	@Column(length=100)
	private String account;//账号
	
	@Column(length=100)
	private String address;//开户地址
	
	@Column(length=100)
	private String idcar;//身份证号
	
	@Column(length=50)
	private String man;//开户人
	
	@Column(length=100)
	private String tel;//电话
	
	@Column(length=50)
	private String time;//开户时间
	
	@Column(length=100)
	private String remarks;//备注
	
	@ManyToOne
	@JoinColumn(name="typeId")
	private ReceiptType type;

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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdcar() {
		return idcar;
	}

	public void setIdcar(String idcar) {
		this.idcar = idcar;
	}

	public String getMan() {
		return man;
	}

	public void setMan(String man) {
		this.man = man;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public ReceiptType getType() {
		return type;
	}

	public void setType(ReceiptType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", name=" + name + ", account=" + account + ", address=" + address + ", idcar="
				+ idcar + ", man=" + man + ", tel=" + tel + ", time=" + time + ", remarks=" + remarks + ", type=" + type
				+ "]";
	}
	
}
