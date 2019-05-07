package com.shenke.entity;

import javax.persistence.*;

//采购商品实体类
@Entity
@Table(name = "t_purchaseGoods")
public class PurchaseGoods {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = true)
    private Double weight;//重量

    @Column(nullable = true)
    private Double price;//价格

    @Column(nullable = true)
    private Double money;//总价

    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private Purchase purchase;//所属的采购单

    @Override
    public String toString() {
        return "PurchaseGoods{" +
                "id=" + id +
                ", weight=" + weight +
                ", price=" + price +
                ", money=" + money +
                ", purchase=" + purchase +
                '}';
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
