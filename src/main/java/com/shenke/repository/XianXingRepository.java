package com.shenke.repository;

import com.shenke.entity.XianXing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XianXingRepository extends JpaSpecificationExecutor<XianXing>, JpaRepository<XianXing, Integer> {

    @Query(value = "select * from t_xian_xing where name like ?1", nativeQuery = true)
    List<XianXing> findByLikeName(String s);
}
