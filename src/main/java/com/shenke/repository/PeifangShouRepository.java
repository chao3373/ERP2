package com.shenke.repository;

import com.shenke.entity.PeiFangShou;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeifangShouRepository extends JpaRepository<PeiFangShou, Integer>, JpaSpecificationExecutor<PeiFangShou> {

    @Query(value = "select * from t_pei_fang_shou where inform_number = ?1", nativeQuery = true)
    List<PeiFangShou> findByInfornNumber(Long informNumber);
}
