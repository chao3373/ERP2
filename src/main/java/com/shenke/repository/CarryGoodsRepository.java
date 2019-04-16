package com.shenke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shenke.entity.CarryGoods;

/**
 * 提货单Repository
 * @author Administrator
 *
 */
public interface CarryGoodsRepository extends JpaRepository<CarryGoods, Long>, JpaSpecificationExecutor<CarryGoods>{
	
}
