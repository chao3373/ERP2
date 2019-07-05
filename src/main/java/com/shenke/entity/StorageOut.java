package com.shenke.entity;

import java.util.Date;

public class StorageOut {

    private String clientname;

    private String peasant;

    private String name;

    private String color;

    private String outNumber;

    private Double model;

    private Double price;

    private Double length;

    private Double weight;

    private Double sumweight;

    private Integer sumnum;

    private Date delivery_time;

    public StorageOut() {

    }

    @Override
    public String toString() {
        return "StorageOut{" +
                "clientname='" + clientname + '\'' +
                ", peasant='" + peasant + '\'' +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", outNumber='" + outNumber + '\'' +
                ", model=" + model +
                ", price=" + price +
                ", length=" + length +
                ", weight=" + weight +
                ", sumweight=" + sumweight +
                ", sumnum=" + sumnum +
                ", delivery_time=" + delivery_time +
                '}';
    }

    public StorageOut(String clientname, String peasant, String name, String color, String outNumber, Double model, Double price, Double length, Double weight, Double sumweight, Integer sumnum, Date delivery_time) {
        this.clientname = clientname;
        this.peasant = peasant;
        this.name = name;
        this.color = color;
        this.outNumber = outNumber;
        this.model = model;
        this.price = price;
        this.length = length;
        this.weight = weight;
        this.sumweight = sumweight;
        this.sumnum = sumnum;
        this.delivery_time = delivery_time;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(String outNumber) {
        this.outNumber = outNumber;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getSumweight() {
        return sumweight;
    }

    public void setSumweight(Double sumweight) {
        this.sumweight = sumweight;
    }

    public Integer getSumnum() {
        return sumnum;
    }

    public void setSumnum(Integer sumnum) {
        this.sumnum = sumnum;
    }

    public Date getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(Date delivery_time) {
        this.delivery_time = delivery_time;
    }
}
