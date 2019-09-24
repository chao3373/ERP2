package com.shenke.service;

import com.shenke.entity.MuLiao;

import java.util.List;

public interface MuLiaoService {

    void save(MuLiao muLiao);

    List<MuLiao> list(MuLiao muLiao, Integer page, Integer rows);

    Long getCount(MuLiao muLiao);

    void delete(Integer id);

    List<MuLiao> findByLikeName(String s);
}
