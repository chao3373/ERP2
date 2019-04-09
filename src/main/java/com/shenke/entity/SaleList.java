package com.shenke.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 销售单实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="t_saleList")
public class SaleList {
	
	@Id
	@GeneratedValue
	private Integer id;//编号
	
	@Column(length=100)
	private String saleNumber;//销售单号
	
	@ManyToOne
	@JoinColumn(name="clientId")
	private Client client;//客户
	
	@ManyToOne
	@JoinColumn(name="sellId")
	private Sell sell;//销售方式
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date saleDate;//销售日期
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date deliveryDate;//发货时间
	
	@ManyToOne
	@JoinColumn(name="clerkId")
	private Clerk clerk;//业务员
	
	@Column(length=50)
	private String lankman;//联系人
	
	@Column(length=100)
	private String tel;//客户电话
	
	@Column(length=500)
	private String address;//地址
	
	@Transient
	private Date bSaleDate;//起始日期 搜索用到
	
	@Transient
	private Date eSaleDate;//结束日期 搜索用到
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;//操作员
	
	@Transient
	private List<SaleListProduct> saleListProduct=null;//销售单商品集合
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Sell getSell() {
		return sell;
	}

	public void setSell(Sell sell) {
		this.sell = sell;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Clerk getClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	public String getLankman() {
		return lankman;
	}

	public void setLankman(String lankman) {
		this.lankman = lankman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getbSaleDate() {
		return bSaleDate;
	}

	public void setbSaleDate(Date bSaleDate) {
		this.bSaleDate = bSaleDate;
	}

	public Date geteSaleDate() {
		return eSaleDate;
	}

	public void seteSaleDate(Date eSaleDate) {
		this.eSaleDate = eSaleDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SaleListProduct> getSaleListProduct() {
		return saleListProduct;
	}

	public void setSaleListProduct(List<SaleListProduct> saleListProduct) {
		this.saleListProduct = saleListProduct;
	}

	@Override
	public String toString() {
		return "SaleList [id=" + id + ", saleNumber=" + saleNumber + ", client=" + client + ", sell=" + sell
				+ ", saleDate=" + saleDate + ", deliveryDate=" + deliveryDate + ", clerk=" + clerk + ", lankman="
				+ lankman + ", tel=" + tel + ", address=" + address + ", bSaleDate=" + bSaleDate + ", eSaleDate="
				+ eSaleDate + ", user=" + user + ", saleListProduct=" + saleListProduct + "]";
	}

}
