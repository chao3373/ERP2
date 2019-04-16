package com.shenke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.shenke.entity.Storage;

/**
 * 入库单Repository
 * @author Administrator
 *
 */

public interface StorageRepository extends JpaRepository<Storage, Integer>, JpaSpecificationExecutor<Storage>{
	
}
