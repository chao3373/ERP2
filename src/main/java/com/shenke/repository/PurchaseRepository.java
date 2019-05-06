package com.shenke.repository;

import com.shenke.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @Description: 采购单Repository
 * @Param:
 * @return:
 * @Author: Andy
 * @Date:
 */
public interface PurchaseRepository extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {

    /**
     * @Description: 查询当天最大的采购单号
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @Query(value = "SELECT MAX(purchase_number) FROM t_purchase WHERE TO_DAYS(purchase_date)=TO_DAYS(NOW())", nativeQuery = true)
    public String getTodayMaxPurchaseNumber();
}
