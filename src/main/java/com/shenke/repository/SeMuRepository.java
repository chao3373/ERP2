package com.shenke.repository;

import com.shenke.entity.SeMu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeMuRepository extends JpaRepository<SeMu, Integer>, JpaSpecificationExecutor<SeMu> {

    @Query(value = "select * from t_se_mu where name like ?1", nativeQuery = true)
    List<SeMu> findByLikeName(String s);
}
