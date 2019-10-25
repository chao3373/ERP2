package com.shenke.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.shenke.entity.SaleList;

import javax.transaction.Transactional;

@Transactional
public interface SaleListRepository extends JpaRepository<SaleList, Integer>, JpaSpecificationExecutor<SaleList> {

	/**
	 * 获取当天最大销售单号
	 * 
	 * @return
	 */
	@Query(value = "SELECT MAX(sale_number) FROM t_sale_list WHERE TO_DAYS(sale_date)=TO_DAYS(NOW())", nativeQuery = true)
	public String getTodayMaxSaleNumber();
	
	/**
	 * 获取所有审核成功的销售订单的id
	 * @return
	 */
	@Query(value="SELECT * FROM t_sale_list WHERE id IN (SELECT sale_list_id FROM t_sale_list_product WHERE state LIKE '%审核失败%' OR state LIKE '%下单%')", nativeQuery = true)
	public List<Integer> getSaleListNo();


	@Query( value = "select * from t_sale_list where sale_number =?1" , nativeQuery = true)
	public List<SaleList> findSaleListId(String saleNumber);

	@Query( value = "select * from t_sale_list where sale_number =?1" , nativeQuery = true)
    SaleList findBySaleNumber(String danhao);

	/***
	 * 根据id修改订金
	 * @param dingjin
	 * @param id
	 */
	@Modifying
	@Query( value = "update t_sale_list set dingjin = ?1 where id = ?2" , nativeQuery = true)
    void updateDingjin(Double dingjin, Integer id);
}
