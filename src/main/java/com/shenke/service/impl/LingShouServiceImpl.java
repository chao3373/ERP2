package com.shenke.service.impl;

import com.shenke.entity.LingShou;
import com.shenke.entity.Storage;
import com.shenke.repository.LingShouRepository;
import com.shenke.repository.StorageRepository;
import com.shenke.service.LingShouService;
import com.shenke.service.StorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("lingShouService")
public class LingShouServiceImpl implements LingShouService {

    @Resource
    private LingShouRepository lingShouRepository;

    @Resource
    private StorageRepository storageRepository;

    /***
     * 查询零售单号
     * @return
     */
    @Override
    public String getTodayMaxSaleNumber() {
        return lingShouRepository.getTodayMaxSaleNumber();
    }

    @Override
    public List<Storage> lingshouList(String s) {
        return storageRepository.lingshouList(s);
    }

    @Override
    public void save(List<LingShou> lingShous) {
        lingShouRepository.save(lingShous);
    }
}
