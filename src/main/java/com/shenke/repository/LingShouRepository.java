package com.shenke.repository;

import com.shenke.entity.LingShou;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LingShouRepository extends JpaRepository<LingShou, Integer>, JpaSpecificationExecutor<LingShou> {

    @Query(value = "SELECT MAX(danhao) FROM t_lingshou WHERE TO_DAYS(xiaoshou_date)=TO_DAYS(NOW())", nativeQuery = true)
    String getTodayMaxSaleNumber();

    @Query(value = "select * from t_lingshou where danhao = ?1", nativeQuery = true)
    List<LingShou> findbydanhao(String danhao);

    @Modifying
    @Query(value = "update t_lingshou set shishou = ?2 where id = ?1", nativeQuery = true)
    void updateShishou(Integer id, Double shishou);
}
