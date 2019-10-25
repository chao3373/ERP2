package com.shenke.service;

import com.shenke.entity.LingShou;
import com.shenke.entity.Storage;

import java.util.List;

public interface LingShouService {
    public String getTodayMaxSaleNumber();

    List<Storage> lingshouList(String s);

    void save(List<LingShou> lingShous);

    List<LingShou> findbydanhao(String danhao);

    LingShou findone(Integer id);

    void saveone(LingShou findone);

    List<LingShou> find(LingShou lingShou);

    void updateShishou(Integer id, Double shishou);
}
