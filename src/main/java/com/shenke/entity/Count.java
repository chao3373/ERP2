package com.shenke.entity;

import java.io.Serializable;
import java.sql.Date;
//根据saleListID 计数
public class Count  implements Serializable {

    private String clientname;//客户名称
    private String peasant;//农户名称
    private String saleNumber;//销售单号
    private String name;//产品名称
    private Double model;//幅宽
    private Double length;//长度
    private Double price;//厚度
    private Double realityweight;//实际重量
    private String deliveryTime;//出库时间
    private Integer sum;//总数量

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getPeasant() {
        return peasant;
    }

    public void setPeasant(String peasant) {
        this.peasant = peasant;
    }

    public String getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(String saleNumber) {
        this.saleNumber = saleNumber;
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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRealityweight() {
        return realityweight;
    }

    public void setRealityweight(Double realityweight) {
        this.realityweight = realityweight;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "Count{" +
                "clientname='" + clientname + '\'' +
                ", peasant='" + peasant + '\'' +
                ", saleNumber='" + saleNumber + '\'' +
                ", name='" + name + '\'' +
                ", model=" + model +
                ", length=" + length +
                ", price=" + price +
                ", realityweight=" + realityweight +
                ", deliveryTime=" + deliveryTime +
                ", sum=" + sum +
                '}';
    }

    public Count(Object clientname, Object peasant, Object saleNumber, Object name, Object model, Object length, Object price, Object realityweight, Object deliveryTime, Object sum) {
        this.clientname = clientname.toString();
        this.peasant = peasant.toString();
        this.saleNumber = saleNumber.toString();
        this.name = name.toString();
        this.model = Double.parseDouble(model.toString());
        this.length = Double.parseDouble(length.toString());
        this.price = Double.parseDouble(price.toString());
        this.realityweight = Double.parseDouble(realityweight.toString());
        this.deliveryTime = deliveryTime.toString();
        this.sum = Integer.parseInt(sum.toString());
    }
}
