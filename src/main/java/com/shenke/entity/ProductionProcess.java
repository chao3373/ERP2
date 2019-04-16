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
 * 生产加工单实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "t_productionProcess")
public class ProductionProcess {
	@Id
	@GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "saleListProductId")
	private SaleListProduct saleListProduct;

	@ManyToOne
	@JoinColumn(name = "jiTaiId")
	private JiTai jiTai;

	@Column(length = 50)
	private String name;// 商品名称

	@Column(nullable = true)
	private Double model;// 幅宽

	@Column(nullable = true)
	private Double price;// 厚度

	@Column(nullable = true)
	private Double length;// 长度

	@Column(length = 50)
	private String color;// 颜色

	@Column(nullable = true)
	private Double realitymodel;// 实际幅宽

	@Column(nullable = true)
	private Double realityprice;// 实际厚度

	@Column(nullable = true)
	private Double realityweight;// 实际重量

	private Integer num;// 数量

	private Integer accomplishNumber;// 完成数量

	@Column(nullable = true)
	private Double theoryweight;// 理论重量

	@Column(nullable = true)
	private Double oneweight;// 单件重量

	@Column(nullable = true)
	private Double square;// 单件平米

	@Column(nullable = true)
	private Double numsquare;// 总平米

	@Column(length = 1000)
	private String demand;// 要求

	@Column(length = 50)
	private String weightway;// 称重方式

	@Column(length = 50)
	private String label;// 标签名称

	@Column(nullable = true)
	private Double weight;// 重量

	@Column(length = 50)
	private String dao;// 剖刀

	@Column(length = 50)
	private String brand;// 商标

	@Column(length = 50)
	private String pack;// 包装

	@Column(length = 50)
	private String letter;// 印字

	@Column(length = 50)
	private String patu;// 纸管

	@Column(length = 50)
	private String wightset;// 重量设置

	@Column(length = 50)
	private String state;// 订单状态

	@Column(nullable = true)
	private Double sumwight;// 总重量

	@Column(nullable = true)
	private Double meter;// 生产米数

	@Column(length = 50)
	private String peasant;// 农户名称

	@Column(length = 50)
	private String clientname;// 客户名称

	@Column(length = 50)
	private Long informNumber;// 通知单号

	@Column(length = 50)
	private String saleNumber;// 订单号

	private Double taskQuantity;// 任务量

	private Date allorTime;// 分配时间

	@Column(length = 50)
	private String allotState;// 分配状态

	@Column(length = 50)
	private String issueState;// 下发状态

	@Column(length = 50)
	private String accomplishState;// 完成状态

	@Transient
	private Date seachTime;// 搜索用到，根据日期查询

	private String remark;// 备注

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SaleListProduct getSaleListProduct() {
		return saleListProduct;
	}

	public void setSaleListProduct(SaleListProduct saleListProduct) {
		this.saleListProduct = saleListProduct;
	}

	public JiTai getJiTai() {
		return jiTai;
	}

	public void setJiTai(JiTai jiTai) {
		this.jiTai = jiTai;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getModel() {
		return model;
	}

	public void setModel(Double model) {
		this.model = model;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Double getRealitymodel() {
		return realitymodel;
	}

	public void setRealitymodel(Double realitymodel) {
		this.realitymodel = realitymodel;
	}

	public Double getRealityprice() {
		return realityprice;
	}

	public void setRealityprice(Double realityprice) {
		this.realityprice = realityprice;
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

	public Integer getAccomplishNumber() {
		return accomplishNumber;
	}

	public void setAccomplishNumber(Integer accomplishNumber) {
		this.accomplishNumber = accomplishNumber;
	}

	public Double getTheoryweight() {
		return theoryweight;
	}

	public void setTheoryweight(Double theoryweight) {
		this.theoryweight = theoryweight;
	}

	public Double getOneweight() {
		return oneweight;
	}

	public void setOneweight(Double oneweight) {
		this.oneweight = oneweight;
	}

	public Double getSquare() {
		return square;
	}

	public void setSquare(Double square) {
		this.square = square;
	}

	public Double getNumsquare() {
		return numsquare;
	}

	public void setNumsquare(Double numsquare) {
		this.numsquare = numsquare;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getWeightway() {
		return weightway;
	}

	public void setWeightway(String weightway) {
		this.weightway = weightway;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getDao() {
		return dao;
	}

	public void setDao(String dao) {
		this.dao = dao;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

	public String getPatu() {
		return patu;
	}

	public void setPatu(String patu) {
		this.patu = patu;
	}

	public String getWightset() {
		return wightset;
	}

	public void setWightset(String wightset) {
		this.wightset = wightset;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Double getSumwight() {
		return sumwight;
	}

	public void setSumwight(Double sumwight) {
		this.sumwight = sumwight;
	}

	public Double getMeter() {
		return meter;
	}

	public void setMeter(Double meter) {
		this.meter = meter;
	}

	public String getPeasant() {
		return peasant;
	}

	public void setPeasant(String peasant) {
		this.peasant = peasant;
	}

	public String getClientname() {
		return clientname;
	}

	public void setClientname(String clientname) {
		this.clientname = clientname;
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

	public Date getSeachTime() {
		return seachTime;
	}

	public void setSeachTime(Date seachTime) {
		this.seachTime = seachTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "ProductionProcess [id=" + id + ", saleListProduct=" + saleListProduct + ", jiTai=" + jiTai + ", name="
				+ name + ", model=" + model + ", price=" + price + ", length=" + length + ", color=" + color
				+ ", realitymodel=" + realitymodel + ", realityprice=" + realityprice + ", realityweight="
				+ realityweight + ", num=" + num + ", accomplishNumber=" + accomplishNumber + ", theoryweight="
				+ theoryweight + ", oneweight=" + oneweight + ", square=" + square + ", numsquare=" + numsquare
				+ ", demand=" + demand + ", weightway=" + weightway + ", label=" + label + ", weight=" + weight
				+ ", dao=" + dao + ", brand=" + brand + ", pack=" + pack + ", letter=" + letter + ", patu=" + patu
				+ ", wightset=" + wightset + ", state=" + state + ", sumwight=" + sumwight + ", meter=" + meter
				+ ", peasant=" + peasant + ", clientname=" + clientname + ", informNumber=" + informNumber
				+ ", saleNumber=" + saleNumber + ", taskQuantity=" + taskQuantity + ", allorTime=" + allorTime
				+ ", allotState=" + allotState + ", issueState=" + issueState + ", accomplishState=" + accomplishState
				+ ", seachTime=" + seachTime + ", remark=" + remark + "]";
	}

}
