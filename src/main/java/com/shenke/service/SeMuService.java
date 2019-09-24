package com.shenke.service;

import com.shenke.entity.SeMu;

import java.util.List;

public interface SeMuService {
    void save(SeMu seMu);

    List<SeMu> list(SeMu seMu, Integer page, Integer rows);

    Long getCount(SeMu seMu);

    void delete(Integer id);

    List<SeMu> findByLikeName(String s);
}
