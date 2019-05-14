package com.shenke.entity;

import java.sql.Date;
import javax.persistence.*;

/**
 * 入库单实体类
 *
 * @author Administrator
 */
@Entity
@Table(name = "t_storage")
public class Storage {

    @Id
    @GeneratedValue
    private Integer id; // 条码

    @ManyToOne
    @JoinColumn(name = "saleListProductId")
    private SaleListProduct saleListProduct;

    @ManyToOne
    @JoinColumn(name = "jitaiProductionAllotId")
    private JitaiProductionAllot jitaiProductionAllot;

    @ManyToOne
    @JoinColumn(name = "jiTaiId")
    private JiTai jiTai;

    @ManyToOne
    @JoinColumn(name = "saleListId")
    private SaleList saleList;

    @ManyToOne
    @JoinColumn(name = "clerkId")
    private Clerk clerk;//员工

    @ManyToOne
    @JoinColumn(name = "locationId")
    private Location location;//仓位

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;//班组

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

    @Column(nullable = true)
    private Double theoryweight;// 理论重量

    @Column(nullable = true)
    private Double oneweight;// 单件重量

    @Column(nullable = true)
    private Double square;// 单件平米

    @Column(nullable = true)
    private Double numsquare;// 总平米

    @Column(length = 1000)
    private String demand;// 要求.

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

    private Date dateInProduced;// 生产日期

    @Column(length = 50)
    private String serialNumber;// 序列号

    private Date deliveryTime;//出库时间

    private Date dateOfDelivery;//提货日期

    private String outNumber;//出库单号

    @Transient
    private Date starDate;//查询用到开始时间

    @Transient
    private Date endDate;//结束时间查询用到

    private String remark;// 备注

    private String printstate;//打印状态

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", saleListProduct=" + saleListProduct +
                ", jitaiProductionAllot=" + jitaiProductionAllot +
                ", jiTai=" + jiTai +
                ", saleList=" + saleList +
                ", clerk=" + clerk +
                ", location=" + location +
                ", group=" + group +
                ", name='" + name + '\'' +
                ", model=" + model +
                ", price=" + price +
                ", length=" + length +
                ", color='" + color + '\'' +
                ", realitymodel=" + realitymodel +
                ", realityprice=" + realityprice +
                ", realityweight=" + realityweight +
                ", num=" + num +
                ", theoryweight=" + theoryweight +
                ", oneweight=" + oneweight +
                ", square=" + square +
                ", numsquare=" + numsquare +
                ", demand='" + demand + '\'' +
                ", weightway='" + weightway + '\'' +
                ", label='" + label + '\'' +
                ", weight=" + weight +
                ", dao='" + dao + '\'' +
                ", brand='" + brand + '\'' +
                ", pack='" + pack + '\'' +
                ", letter='" + letter + '\'' +
                ", patu='" + patu + '\'' +
                ", wightset='" + wightset + '\'' +
                ", state='" + state + '\'' +
                ", sumwight=" + sumwight +
                ", meter=" + meter +
                ", peasant='" + peasant + '\'' +
                ", clientname='" + clientname + '\'' +
                ", informNumber=" + informNumber +
                ", saleNumber='" + saleNumber + '\'' +
                ", productionMessage='" + productionMessage + '\'' +
                ", taskQuantity=" + taskQuantity +
                ", allorTime=" + allorTime +
                ", allotState='" + allotState + '\'' +
                ", issueState='" + issueState + '\'' +
                ", accomplishState='" + accomplishState + '\'' +
                ", dateInProduced=" + dateInProduced +
                ", serialNumber='" + serialNumber + '\'' +
                ", deliveryTime=" + deliveryTime +
                ", dateOfDelivery=" + dateOfDelivery +
                ", outNumber='" + outNumber + '\'' +
                ", starDate=" + starDate +
                ", endDate=" + endDate +
                ", remark='" + remark + '\'' +
                ", printstate='" + printstate + '\'' +
                '}';
    }

    public String getPrintstate() {
        return printstate;
    }

    public void setPrintstate(String printstate) {
        this.printstate = printstate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

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

    public JitaiProductionAllot getJitaiProductionAllot() {
        return jitaiProductionAllot;
    }

    public void setJitaiProductionAllot(JitaiProductionAllot jitaiProductionAllot) {
        this.jitaiProductionAllot = jitaiProductionAllot;
    }

    public JiTai getJiTai() {
        return jiTai;
    }

    public void setJiTai(JiTai jiTai) {
        this.jiTai = jiTai;
    }

    public SaleList getSaleList() {
        return saleList;
    }

    public void setSaleList(SaleList saleList) {
        this.saleList = saleList;
    }

    public Clerk getClerk() {
        return clerk;
    }

    public void setClerk(Clerk clerk) {
        this.clerk = clerk;
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

    public Date getDateInProduced() {
        return dateInProduced;
    }

    public void setDateInProduced(Date dateInProduced) {
        this.dateInProduced = dateInProduced;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
    }

    public Date getStarDate() {
        return starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
