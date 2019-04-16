package com.shenke.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 机台生产分配实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_jitaiProductionAllot")
public class JitaiProductionAllot {
	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	@JoinColumn(name = "JitaiId")
	private JiTai jiTai;// 所分配的机台

	@ManyToOne
	@JoinColumn(name = "saleListProductId")
	private SaleListProduct saleListProduct;// 所对应的销售订单产品信息

	@Column(length = 50)
	private Long informNumber;// 通知单号

	@Column(length = 50)
	private String saleNumber;// 订单号

	@Column(length = 500)
	private String productionMessage;// 产品信息

	private Double taskQuantity;// 任务量

	private Date allorTime;// 分配时间

	@Column(length = 50)
	private String allotState;// 分配状态

	@Column(length = 50)
	private String issueState;// 下发状态

	@Column(length = 50)
	private String accomplishState;// 完成状态

	private Double realityweight;// 实际重量

	private Integer num;// 数量

	@Transient
	private Date seachTime;// 搜索用到，根据日期查询

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public JiTai getJiTai() {
		return jiTai;
	}

	public void setJiTai(JiTai jiTai) {
		this.jiTai = jiTai;
	}

	public SaleListProduct getSaleListProduct() {
		return saleListProduct;
	}

	public void setSaleListProduct(SaleListProduct saleListProduct) {
		this.saleListProduct = saleListProduct;
	}

	public Long getInformNumber() {
		return informNumber;
	}

	public void setInformNumber(Long informNumber) {
		this.informNumber = informNumber;
	}

	public String getSaleNumber() {
		return saleNumber;
	}

	public void setSaleNumber(String saleNumber) {
		this.saleNumber = saleNumber;
	}

	public String getProductionMessage() {
		return productionMessage;
	}

	public void setProductionMessage(String productionMessage) {
		this.productionMessage = productionMessage;
	}

	public Double getTaskQuantity() {
		return taskQuantity;
	}

	public void setTaskQuantity(Double taskQuantity) {
		this.taskQuantity = taskQuantity;
	}

	public Date getAllorTime() {
		return allorTime;
	}

	public void setAllorTime(Date allorTime) {
		this.allorTime = allorTime;
	}

	public String getAllotState() {
		return allotState;
	}

	public void setAllotState(String allotState) {
		this.allotState = allotState;
	}

	public String getIssueState() {
		return issueState;
	}

	public void setIssueState(String issueState) {
		this.issueState = issueState;
	}

	public String getAccomplishState() {
		return accomplishState;
	}

	public void setAccomplishState(String accomplishState) {
		this.accomplishState = accomplishState;
	}

	public Double getRealityweight() {
		return realityweight;
	}

	public void setRealityweight(Double realityweight) {
		this.realityweight = realityweight;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getSeachTime() {
		return seachTime;
	}

	public void setSeachTime(Date seachTime) {
		this.seachTime = seachTime;
	}

	@Override
	public String toString() {
		return "JitaiProductionAllot [id=" + id + ", jiTai=" + jiTai + ", saleListProduct=" + saleListProduct
				+ ", informNumber=" + informNumber + ", saleNumber=" + saleNumber + ", productionMessage="
				+ productionMessage + ", taskQuantity=" + taskQuantity + ", allorTime=" + allorTime + ", allotState="
				+ allotState + ", issueState=" + issueState + ", accomplishState=" + accomplishState
				+ ", realityweight=" + realityweight + ", num=" + num + ", seachTime=" + seachTime + "]";
	}

}
