package com.shenke.service;

import com.shenke.entity.PeiFangShou;

import java.util.List;

public interface PeiFangShouService {

    void saveList(List<PeiFangShou> plgList);

    List<PeiFangShou> findByInfornNumber(Long informNumber);

    void deleteList(List<PeiFangShou> peiFangShous);
}
