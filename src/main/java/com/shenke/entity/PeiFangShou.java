package com.shenke.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_peiFangShou")
public class PeiFangShou {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 100)
    private String ceng;

    @Column(length = 100)
    private String muliao;

    @Column(length = 100)
    private String xianxing;

    @Column(length = 100)
    private String gaoya;

    @Column(length = 100)
    private String maojinshu;

    @Column(length = 100)
    private String semu;

    @Column(length = 100)
    private String qita;

    private Long informNumber;

    @Override
    public String toString() {
        return "PeiFangShou{" +
                "id=" + id +
                ", ceng='" + ceng + '\'' +
                ", muliao='" + muliao + '\'' +
                ", xianxing='" + xianxing + '\'' +
                ", gaoya='" + gaoya + '\'' +
                ", maojinshu='" + maojinshu + '\'' +
                ", semu='" + semu + '\'' +
                ", qita='" + qita + '\'' +
                ", informNumber=" + informNumber +
                '}';
    }

    public Long getInformNumber() {
        return informNumber;
    }

    public void setInformNumber(Long informNumber) {
        this.informNumber = informNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCeng() {
        return ceng;
    }

    public void setCeng(String ceng) {
        this.ceng = ceng;
    }

    public String getMuliao() {
        return muliao;
    }

    public void setMuliao(String muliao) {
        this.muliao = muliao;
    }

    public String getXianxing() {
        return xianxing;
    }

    public void setXianxing(String xianxing) {
        this.xianxing = xianxing;
    }

    public String getGaoya() {
        return gaoya;
    }

    public void setGaoya(String gaoya) {
        this.gaoya = gaoya;
    }

    public String getMaojinshu() {
        return maojinshu;
    }

    public void setMaojinshu(String maojinshu) {
        this.maojinshu = maojinshu;
    }

    public String getSemu() {
        return semu;
    }

    public void setSemu(String semu) {
        this.semu = semu;
    }

    public String getQita() {
        return qita;
    }

    public void setQita(String qita) {
        this.qita = qita;
    }
}

