package com.shenke.repository;

import com.shenke.entity.LingShou;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LingShouRepository extends JpaRepository<LingShou, Integer>, JpaSpecificationExecutor<LingShou> {

    @Query(value = "SELECT MAX(danhao) FROM t_lingshou WHERE TO_DAYS(xiaoshou_date)=TO_DAYS(NOW())", nativeQuery = true)
    String getTodayMaxSaleNumber();
}
