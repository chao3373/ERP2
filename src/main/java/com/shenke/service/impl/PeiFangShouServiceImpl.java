package com.shenke.service.impl;

import com.shenke.entity.PeiFangShou;
import com.shenke.repository.PeifangShouRepository;
import com.shenke.service.PeiFangShouService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("peiFangShouService")
public class PeiFangShouServiceImpl implements PeiFangShouService {
    @Resource
    private PeifangShouRepository peifangShouRepository;

    @Override
    public void saveList(List<PeiFangShou> plgList) {
        peifangShouRepository.save(plgList);
    }

    @Override
    public List<PeiFangShou> findByInfornNumber(Long informNumber) {
        return peifangShouRepository.findByInfornNumber(informNumber);
    }

    @Override
    public void deleteList(List<PeiFangShou> peiFangShous) {
        peifangShouRepository.delete(peiFangShous);
    }
}
