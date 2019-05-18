package com.shenke.service.impl;

import com.shenke.entity.Purchase;
import com.shenke.repository.PurchaseRepository;
import com.shenke.service.PurchaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseRepository purchaseRepository;

    /**
     * @Description: 获取当天最大的采购单号
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @Override
    public String getTodayMaxPurchaseNumber() {
        return purchaseRepository.getTodayMaxPurchaseNumber();
    }

    @Override
    public void save(Purchase purchase) {
        purchaseRepository.save(purchase);
    }
}
