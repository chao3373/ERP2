package com.shenke.repository;

import com.shenke.entity.SaleReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SaleReturnRepository extends JpaRepository<SaleReturn, Integer>, JpaSpecificationExecutor<SaleReturn> {

}
