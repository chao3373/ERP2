package com.shenke.service;

import com.shenke.entity.MaoJinShu;

import java.util.List;

public interface MaoJinShuService {
    void save(MaoJinShu maoJinShu);

    List<MaoJinShu> list(MaoJinShu maoJinShu, Integer page, Integer rows);

    Long getCount(MaoJinShu maoJinShu);

    void delete(Integer id);

    List<MaoJinShu> findByLikeName(String s);
}
