package com.shenke.repository;

import com.shenke.entity.GaoYa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GaoyaRepository extends JpaRepository<GaoYa, Integer>, JpaSpecificationExecutor<GaoYa> {

    @Query(value = "select * from t_gao_ya where name like ?1", nativeQuery = true)
    List<GaoYa> findByLikeName(String s);
}
