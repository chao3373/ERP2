package com.shenke.repository;

import com.shenke.entity.PeiFang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PeiFangRepository extends JpaRepository<PeiFang, Integer>, JpaSpecificationExecutor<PeiFang> {

    @Query(value = "select * from t_pei_fang where name = ?1", nativeQuery = true)
    List<PeiFang> findByName(String name);

    @Query(value = "select * from t_pei_fang where name like ?1", nativeQuery = true)
    List<PeiFang> findByLikeName(String s);
}
