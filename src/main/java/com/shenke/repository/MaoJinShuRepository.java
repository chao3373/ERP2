package com.shenke.repository;

import com.shenke.entity.MaoJinShu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaoJinShuRepository extends JpaRepository<MaoJinShu, Integer>, JpaSpecificationExecutor<MaoJinShu> {

    @Query(value = "select * from t_mao_jin_shu where name like ?1", nativeQuery = true)
    List<MaoJinShu> findByLikeName(String s);
}
