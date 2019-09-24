package com.shenke.repository;

import com.shenke.entity.QiTa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QiTaRepository extends JpaSpecificationExecutor<QiTa>, JpaRepository<QiTa, Integer> {

    @Query(value = "select * from t_qi_ta where name like ?1", nativeQuery = true)
    List<QiTa> findByLikeName(String s);
}
