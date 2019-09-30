package com.shenke.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_lingshou")
public class LingShou {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String danhao;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String clientname;

    @Column(precision = 6, scale = 2)
    private Double length;

    @Column(precision = 6, scale = 2)
    private Double model;

    @Column(precision = 6, scale = 3)
    private Double price;

    @Column(precision = 6, scale = 2)
    private Double weight;

    @Column(precision = 6, scale = 2)
    private Double danjia;

    @Column(precision = 6, scale = 2)
    private Double yingshou;

    @Column(precision = 6, scale = 2)
    private Double shishou;

    private Integer num;

    private Integer storageid;

    private Date xiaoshouDate;

    @Transient
    private Date starDate;

    @Transient
    private Date endDate;

    @Override
    public String toString() {
        return "LingShou{" +
                "id=" + id +
                ", danhao='" + danhao + '\'' +
                ", name='" + name + '\'' +
                ", clientname='" + clientname + '\'' +
                ", length=" + length +
                ", model=" + model +
                ", price=" + price +
                ", weight=" + weight +
                ", danjia=" + danjia +
                ", yingshou=" + yingshou +
                ", shishou=" + shishou +
                ", num=" + num +
                ", storageid=" + storageid +
                ", xiaoshouDate=" + xiaoshouDate +
                ", starDate=" + starDate +
                ", endDate=" + endDate +
                '}';
    }

    public Double getDanjia() {
        return danjia;
    }

    public void setDanjia(Double danjia) {
        this.danjia = danjia;
    }

    public Integer getStorageid() {
        return storageid;
    }

    public void setStorageid(Integer storageid) {
        this.storageid = storageid;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public Double getYingshou() {
        return yingshou;
    }

    public void setYingshou(Double yingshou) {
        this.yingshou = yingshou;
    }

    public Double getShishou() {
        return shishou;
    }

    public void setShishou(Double shishou) {
        this.shishou = shishou;
    }

    public Double getJine() {
        return danjia;
    }

    public void setJine(Double danjia) {
        this.danjia = danjia;
    }

    public String getDanhao() {
        return danhao;
    }

    public void setDanhao(String danhao) {
        this.danhao = danhao;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

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

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
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

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Date getXiaoshouDate() {
        return xiaoshouDate;
    }

    public void setXiaoshouDate(Date xiaoshouDate) {
        this.xiaoshouDate = xiaoshouDate;
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
}
