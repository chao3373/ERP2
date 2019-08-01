package com.shenke.entity;

public class Month {

    private Integer lastMonth;

    private Integer monthIn;

    private Integer monthOut;

    private Integer kuCun;

    public Month() {

    }

    public Month(Integer lastMonth, Integer monthIn, Integer monthOut, Integer kuCun) {
        this.lastMonth = lastMonth;
        this.monthIn = monthIn;
        this.monthOut = monthOut;
        this.kuCun = kuCun;
    }

    @Override
    public String toString() {
        return "Month{" +
                "lastMonth=" + lastMonth +
                ", monthIn=" + monthIn +
                ", monthOut=" + monthOut +
                ", kuCun=" + kuCun +
                '}';
    }

    public Integer getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(Integer lastMonth) {
        this.lastMonth = lastMonth;
    }

    public Integer getMonthIn() {
        return monthIn;
    }

    public void setMonthIn(Integer monthIn) {
        this.monthIn = monthIn;
    }

    public Integer getMonthOut() {
        return monthOut;
    }

    public void setMonthOut(Integer monthOut) {
        this.monthOut = monthOut;
    }

    public Integer getKuCun() {
        return kuCun;
    }

    public void setKuCun(Integer kuCun) {
        this.kuCun = kuCun;
    }
}