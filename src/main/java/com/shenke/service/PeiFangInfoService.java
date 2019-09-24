package com.shenke.service;

import com.shenke.entity.PeiFangInfo;

import java.util.List;


public interface PeiFangInfoService {

    List<PeiFangInfo> list(PeiFangInfo peiFangInfo);

    List<PeiFangInfo> findByPeiFangId(Integer id);

    void delete(List<PeiFangInfo> peiFangInfos);

    void save(PeiFangInfo peiFangInfo);

    PeiFangInfo findByCengAndPeiFangId(String ceng, Integer id);
}
