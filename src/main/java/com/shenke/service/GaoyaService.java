package com.shenke.service;

import com.shenke.entity.GaoYa;

import java.util.List;

public interface GaoyaService {

    void save(GaoYa gaoya);

    List<GaoYa> list(GaoYa gaoya, Integer page, Integer rows);

    Long getCount(GaoYa gaoya);

    void delete(Integer id);

    List<GaoYa> findByLikeName(String s);
}
