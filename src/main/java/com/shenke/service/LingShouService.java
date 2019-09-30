package com.shenke.service;

import com.shenke.entity.LingShou;
import com.shenke.entity.Storage;

import java.util.List;

public interface LingShouService {
    public String getTodayMaxSaleNumber();

    List<Storage> lingshouList(String s);

    void save(List<LingShou> lingShous);
}
