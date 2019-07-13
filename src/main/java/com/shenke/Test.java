package com.shenke;

import java.sql.Date;

public class Test {
    public static void main(String[] args){
        Date sqlDate = new Date(new java.util.Date().getTime());
        System.out.println(sqlDate);

        java.util.Date utDate = new java.util.Date();
        System.out.println(utDate);
    }
}
