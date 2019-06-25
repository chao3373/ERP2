package com.shenke.service.impl;

import com.shenke.entity.SaleReturn;
import com.shenke.repository.SaleReturnRepository;
import com.shenke.service.SaleReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("saleReturnService")
public class SaleReturnServiceImpl implements SaleReturnService {

    @Resource
    private SaleReturnRepository saleReturnRepository;

    @Override
    public void add(SaleReturn saleReturn) {
        saleReturnRepository.save(saleReturn);
    }

    @Override
    public List<SaleReturn> findAll() {
        return saleReturnRepository.findAll();
    }
}
