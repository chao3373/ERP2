package com.shenke.repository;

import com.shenke.entity.PeiFangInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeiFangInfoRepository extends JpaRepository<PeiFangInfo, Integer>, JpaSpecificationExecutor<PeiFangInfo> {

    @Query(value = "select * from t_pei_fang_info where peifangid = ?1 ORDER BY ceng", nativeQuery = true)
    List<PeiFangInfo> findByPeiFangId(Integer id);

    @Query(value = "select * from t_pei_fang_info where peifangid = ?2 and ceng = ?1", nativeQuery = true)
    PeiFangInfo findByCengAndPeiFangId(String ceng, Integer id);
}
