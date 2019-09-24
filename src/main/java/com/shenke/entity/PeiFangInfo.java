package com.shenke.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_peiFangInfo")
public class PeiFangInfo {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String muliaoname;

    @Column(length = 50)
    private String xianxingname;

    @Column(length = 50)
    private String gaoyaname;

    @Column(length = 50)
    private String maojinshuname;

    @Column(length = 50)
    private String semuname;

    @Column(length = 50)
    private String qitaname;

    @Column(length = 10)
    private String ceng;

    private Double muliaonum;

    private Double xianxingnum;

    private Double gaoyanum;

    private Double maojinshunum;

    private Double semunum;

    private Double qitanum;

    @ManyToOne
    @JoinColumn(name = "peifangid")
    private PeiFang peiFang;

    @Transient
    private String muliaoinfo;

    @Transient
    private String xianxinginfo;

    @Transient
    private String gaoyainfo;

    @Transient
    private String maojinshuinfo;

    @Transient
    private String semuinfo;

    @Transient
    private String qitainfo;

    @Override
    public String toString() {
        return "PeiFangInfo{" +
                "id=" + id +
                ", muliaoname='" + muliaoname + '\'' +
                ", xianxingname='" + xianxingname + '\'' +
                ", gaoyaname='" + gaoyaname + '\'' +
                ", maojinshuname='" + maojinshuname + '\'' +
                ", semuname='" + semuname + '\'' +
                ", qitaname='" + qitaname + '\'' +
                ", ceng='" + ceng + '\'' +
                ", muliaonum=" + muliaonum +
                ", xianxingnum=" + xianxingnum +
                ", gaoyanum=" + gaoyanum +
                ", maojinshunum=" + maojinshunum +
                ", semunum=" + semunum +
                ", qitanum=" + qitanum +
                ", peiFang=" + peiFang +
                ", muliaoinfo='" + muliaoinfo + '\'' +
                ", xianxinginfo='" + xianxinginfo + '\'' +
                ", gaoyainfo='" + gaoyainfo + '\'' +
                ", maojinshuinfo='" + maojinshuinfo + '\'' +
                ", semuinfo='" + semuinfo + '\'' +
                ", qitainfo='" + qitainfo + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMuliaoname() {
        return muliaoname;
    }

    public void setMuliaoname(String muliaoname) {
        this.muliaoname = muliaoname;
    }

    public String getXianxingname() {
        return xianxingname;
    }

    public void setXianxingname(String xianxingname) {
        this.xianxingname = xianxingname;
    }

    public String getGaoyaname() {
        return gaoyaname;
    }

    public void setGaoyaname(String gaoyaname) {
        this.gaoyaname = gaoyaname;
    }

    public String getMaojinshuname() {
        return maojinshuname;
    }

    public void setMaojinshuname(String maojinshuname) {
        this.maojinshuname = maojinshuname;
    }

    public String getSemuname() {
        return semuname;
    }

    public void setSemuname(String semuname) {
        this.semuname = semuname;
    }

    public String getQitaname() {
        return qitaname;
    }

    public void setQitaname(String qitaname) {
        this.qitaname = qitaname;
    }

    public String getCeng() {
        return ceng;
    }

    public void setCeng(String ceng) {
        this.ceng = ceng;
    }

    public Double getMuliaonum() {
        return muliaonum;
    }

    public void setMuliaonum(Double muliaonum) {
        this.muliaonum = muliaonum;
    }

    public Double getXianxingnum() {
        return xianxingnum;
    }

    public void setXianxingnum(Double xianxingnum) {
        this.xianxingnum = xianxingnum;
    }

    public Double getGaoyanum() {
        return gaoyanum;
    }

    public void setGaoyanum(Double gaoyanum) {
        this.gaoyanum = gaoyanum;
    }

    public Double getMaojinshunum() {
        return maojinshunum;
    }

    public void setMaojinshunum(Double maojinshunum) {
        this.maojinshunum = maojinshunum;
    }

    public Double getSemunum() {
        return semunum;
    }

    public void setSemunum(Double semunum) {
        this.semunum = semunum;
    }

    public Double getQitanum() {
        return qitanum;
    }

    public void setQitanum(Double qitanum) {
        this.qitanum = qitanum;
    }

    public PeiFang getPeiFang() {
        return peiFang;
    }

    public void setPeiFang(PeiFang peiFang) {
        this.peiFang = peiFang;
    }

    public String getMuliaoinfo() {
        return muliaoinfo;
    }

    public void setMuliaoinfo(String muliaoinfo) {
        this.muliaoinfo = muliaoinfo;
    }

    public String getXianxinginfo() {
        return xianxinginfo;
    }

    public void setXianxinginfo(String xianxinginfo) {
        this.xianxinginfo = xianxinginfo;
    }

    public String getGaoyainfo() {
        return gaoyainfo;
    }

    public void setGaoyainfo(String gaoyainfo) {
        this.gaoyainfo = gaoyainfo;
    }

    public String getMaojinshuinfo() {
        return maojinshuinfo;
    }

    public void setMaojinshuinfo(String maojinshuinfo) {
        this.maojinshuinfo = maojinshuinfo;
    }

    public String getSemuinfo() {
        return semuinfo;
    }

    public void setSemuinfo(String semuinfo) {
        this.semuinfo = semuinfo;
    }

    public String getQitainfo() {
        return qitainfo;
    }

    public void setQitainfo(String qitainfo) {
        this.qitainfo = qitainfo;
    }
}
