package com.shenke.repository;

import com.shenke.entity.MuLiao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MuLiaoRepository extends JpaRepository<MuLiao, Integer>, JpaSpecificationExecutor<MuLiao> {

    @Query(value = "select * from t_muliao where name like ?1", nativeQuery = true)
    List<MuLiao> findByLikeName(String s);
}
