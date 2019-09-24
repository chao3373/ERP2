package com.shenke.service;

import com.shenke.entity.PeiFang;

import java.util.List;

public interface PeiFangService {

    List<PeiFang> findAll();

    boolean add(PeiFang peiFang);

    List<PeiFang> findByName(String s);

    void delete(Integer id);
}
