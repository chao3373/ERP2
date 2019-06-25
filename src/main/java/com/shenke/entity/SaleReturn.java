package com.shenke.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_saleReturn")
public class SaleReturn {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(nullable = true)
    private Double model;

    @Column(nullable = true)
    private Double price;

    @Column(nullable = true)
    private Double weight;

    @Column(nullable = true)
    private Double length;

    @Column(length = 50)
    private String color;

    @Override
    public String toString() {
        return "SaleReturn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model=" + model +
                ", price=" + price +
                ", weight=" + weight +
                ", length=" + length +
                ", color='" + color + '\'' +
                '}';
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
}
