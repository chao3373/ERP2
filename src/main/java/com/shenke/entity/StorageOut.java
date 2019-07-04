package com.shenke.entity;

public class StorageOut {

    private String name;

    private String color;

    private String outNumber;

    private String model;

    private String price;

    private String length;

    private String weight;

    private String sumweight;

    private String sumnum;

    public StorageOut() {

    }

    public StorageOut(String name, String color, String outNumber, String model, String price, String length, String weight, String sumweight, String sumnum) {
        this.name = name;
        this.color = color;
        this.outNumber = outNumber;
        this.model = model;
        this.price = price;
        this.length = length;
        this.weight = weight;
        this.sumweight = sumweight;
        this.sumnum = sumnum;
    }

    @Override
    public String toString() {
        return "StorageOut{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", outNumber='" + outNumber + '\'' +
                ", model='" + model + '\'' +
                ", price='" + price + '\'' +
                ", length='" + length + '\'' +
                ", weight='" + weight + '\'' +
                ", sumweight='" + sumweight + '\'' +
                ", sumnum='" + sumnum + '\'' +
                '}';
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSumweight() {
        return sumweight;
    }

    public void setSumweight(String sumweight) {
        this.sumweight = sumweight;
    }

    public String getSumnum() {
        return sumnum;
    }

    public void setSumnum(String sumnum) {
        this.sumnum = sumnum;
    }
}
