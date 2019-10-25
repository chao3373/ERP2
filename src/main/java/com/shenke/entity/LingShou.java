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

    @Column(scale = 2)
    private Double length;

    @Column(scale = 2)
    private Double model;

    @Column(scale = 2)
    private Double midu;

    @Column(scale = 3)
    private Double price;

    @Column(scale = 2)
    private Double weight;

    @Column(scale = 2)
    private Double danjia;

    @Column(scale = 2)
    private Double yingshou;

    @Column(scale = 2)
    private Double shishou;

    private String tel;//联系电话

    @Column(scale = 2)
    private Double dingjin;//订金

    private String peasant;//农户

    private Integer num;

    private Integer storageid;

    private Date xiaoshouDate;

    @Column(precision = 6, scale = 2)
    private Double qita;

    private String beizhu;//备注

    private String address;//地址

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
                ", midu=" + midu +
                ", price=" + price +
                ", weight=" + weight +
                ", danjia=" + danjia +
                ", yingshou=" + yingshou +
                ", shishou=" + shishou +
                ", tel='" + tel + '\'' +
                ", dingjin=" + dingjin +
                ", peasant='" + peasant + '\'' +
                ", num=" + num +
                ", storageid=" + storageid +
                ", xiaoshouDate=" + xiaoshouDate +
                ", qita=" + qita +
                ", beizhu='" + beizhu + '\'' +
                ", address='" + address + '\'' +
                ", starDate=" + starDate +
                ", endDate=" + endDate +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Double getDingjin() {
        return dingjin;
    }

    public void setDingjin(Double dingjin) {
        this.dingjin = dingjin;
    }

    public String getPeasant() {
        return peasant;
    }

    public void setPeasant(String peasant) {
        this.peasant = peasant;
    }

    public Double getQita() {
        return qita;
    }

    public void setQita(Double qita) {
        this.qita = qita;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public Double getMidu() {
        return midu;
    }

    public void setMidu(Double midu) {
        this.midu = midu;
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
