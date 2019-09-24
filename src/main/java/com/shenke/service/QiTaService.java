package com.shenke.service;

import com.shenke.entity.QiTa;

import java.util.List;

public interface QiTaService {

    void save(QiTa qiTa);

    List<QiTa> list(QiTa qiTa, Integer page, Integer rows);

    Long getCount(QiTa qiTa);

    void delete(Integer id);

    List<QiTa> findByLikeName(String s);
}
