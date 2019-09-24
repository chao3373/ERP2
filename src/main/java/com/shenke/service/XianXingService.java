package com.shenke.service;

import com.shenke.entity.XianXing;

import java.util.List;

public interface XianXingService {

    void save(XianXing xianXing);

    List<XianXing> list(XianXing xianXing, Integer page, Integer rows);

    Long getCount(XianXing xianXing);

    void delete(Integer id);

    List<XianXing> findByLikeName(String s);
}
