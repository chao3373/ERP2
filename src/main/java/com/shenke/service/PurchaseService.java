package com.shenke.service;

import com.shenke.entity.Purchase;

public interface PurchaseService {

    /** 
    * @Description: 获取当天最大的采购单号 
    * @Param:
    * @return:  
    * @Author: Andy
    * @Date:  
    */
    public String getTodayMaxPurchaseNumber();

    public void save(Purchase purchase);

}
