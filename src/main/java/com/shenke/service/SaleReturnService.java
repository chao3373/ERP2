package com.shenke.service;

import com.shenke.entity.SaleReturn;

import java.util.List;

public interface SaleReturnService {

    void add(SaleReturn saleReturn);

    List<SaleReturn> findAll();
}
