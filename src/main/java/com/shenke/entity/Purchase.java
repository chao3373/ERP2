package com.shenke.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * @Description: 采购单实体类
 * @Param:
 * @return:
 * @Author: Andy
 * @Date:
 */
@Entity
@Table(name = "t_purchase")
public class Purchase {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "plantId")
    private Plant plant;//供货商

    @Column(length = 50)
    private String PurchaseNumber;//采购单号

    @Column(length = 50)
    private String principal;//负责人

    @Column(length = 50)
    private String PurchasingAgent;//采购人

    private Date purchaseDate;//采购日期

    private Date outDate;//发货日期

    @Column(length = 50)
    private String carNumber;//车牌号

    @Column(nullable = true)
    private Double tonnage;//吨位，载重量

    @Column(length = 50)
    private String carrier;//承运人，运送者

    @Column(nullable = true)
    private Double freight;//运费

    @Column(length = 50)
    private String paymentState;//付款情况

    private Date paymentDate;//付款日期

    @Column(nullable = true)
    private Double paymentMoney;//付款金额

    @Column(nullable = true)
    private Double arrearageMoney;//未付金额

    @Column(nullable = true)
    private Double sumMoney;//总金额

    @Column(nullable = true)
    private Double sumWeight;//总重量

    @Column(length = 500)
    private String remark;//备注

    @Transient
    private Date startDate;//开始日期，查询用

    @Transient
    private Date endDate;//结束日期，查询用

    @Transient
    private List<PurchaseGoods>  purchaseGoodsList = null;//该采购单中采购商品的集合

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", plant=" + plant +
                ", PurchaseNumber='" + PurchaseNumber + '\'' +
                ", principal='" + principal + '\'' +
                ", PurchasingAgent='" + PurchasingAgent + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", outDate=" + outDate +
                ", carNumber='" + carNumber + '\'' +
                ", tonnage=" + tonnage +
                ", carrier='" + carrier + '\'' +
                ", freight=" + freight +
                ", paymentState='" + paymentState + '\'' +
                ", paymentDate=" + paymentDate +
                ", paymentMoney=" + paymentMoney +
                ", arrearageMoney=" + arrearageMoney +
                ", sumMoney=" + sumMoney +
                ", sumWeight=" + sumWeight +
                ", remark='" + remark + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", purchaseGoodsList=" + purchaseGoodsList +
                '}';
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public Double getSumWeight() {
        return sumWeight;
    }

    public void setSumWeight(Double sumWeight) {
        this.sumWeight = sumWeight;
    }

    public List<PurchaseGoods> getPurchaseGoodsList() {
        return purchaseGoodsList;
    }

    public void setPurchaseGoodsList(List<PurchaseGoods> purchaseGoodsList) {
        this.purchaseGoodsList = purchaseGoodsList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPurchaseNumber() {
        return PurchaseNumber;
    }

    public void setPurchaseNumber(String purchaseNumber) {
        PurchaseNumber = purchaseNumber;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getPurchasingAgent() {
        return PurchasingAgent;
    }

    public void setPurchasingAgent(String purchasingAgent) {
        PurchasingAgent = purchasingAgent;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public Double getTonnage() {
        return tonnage;
    }

    public void setTonnage(Double tonnage) {
        this.tonnage = tonnage;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public Double getFreight() {
        return freight;
    }

    public void setFreight(Double freight) {
        this.freight = freight;
    }

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getPaymentMoney() {
        return paymentMoney;
    }

    public void setPaymentMoney(Double paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public Double getArrearageMoney() {
        return arrearageMoney;
    }

    public void setArrearageMoney(Double arrearageMoney) {
        this.arrearageMoney = arrearageMoney;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
