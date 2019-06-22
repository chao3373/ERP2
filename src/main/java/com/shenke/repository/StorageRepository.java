package com.shenke.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.shenke.entity.Storage;

/**
 * 入库单Repository
 *
 * @author Administrator
 */

public interface StorageRepository extends JpaRepository<Storage, Integer>, JpaSpecificationExecutor<Storage> {

    /**
     * 根据分配的盘点机查询
     *
     * @param serialNumber
     * @return
     */
    @Query(value = "select * from t_storage where serial_number = ?1", nativeQuery = true)
    public List<Storage> fandAllBySerialNumber(String serialNumber);

    /**
     * 查询id最大的
     *
     * @return
     */
    @Query(value = "SELECT * FROM t_storage ORDER BY id DESC LIMIT 0,1", nativeQuery = true)
    public Storage selectByMaxId();

    /**
     * 修改为出库
     */
    @Modifying
    @Query(value = "update t_storage set state = '提货', date_of_delivery = ?2 where id =?1", nativeQuery = true)
    public void outStorage(int id, Date date);


    /**
     * 根据是否已出库来查询
     *
     * @Query(value = "select * from t_sale_list_product where state like '%审核通过%'", nativeQuery = true)
     * public List<SaleListProduct> listProductSucceed();
     */

    @Query(value = "select  * from t_storage where id not in (select id from t_storage where state like'提货' or state like '装车' or state like '准备提货')",nativeQuery = true)
    public List<Storage> outSuccess();

    /**
     * 盘点机序列号
     */
    @Modifying
    @Query(value = "update t_storage set serial_number =?1 where id =?2", nativeQuery = true)
    public void gai(String serial_number, int id);

    /**
     * 根据id修改状态
    * @Description:
    * @Param:  
    * @return:  
    * @Author: Andy
    * @Date:  
    */
    @Modifying
    @Query(value = "update t_storage set  state = ?1, delivery_time = ?3 where id = ?2", nativeQuery = true)
    public void updateStateById(String state, Integer id, Date date);

    /**
     * 根据客户名称查询并按照商品名排序
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @Query(value = "SELECT NAME, COUNT(*) FROM t_storage WHERE clientname = ?1 GROUP BY NAME ", nativeQuery = true)
    public List<Object[]> findByClientAndGroupByName(String client);

    /**
     * 查询所有生产完成的商品
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @Query(value = "select * from t_storage", nativeQuery = true)
    public List<Storage> findAll();

    /**
     * 获取当天最大出库单号
     *
     * @return
     */
    @Query(value = "SELECT MAX(out_number) FROM t_storage WHERE TO_DAYS(delivery_time)=TO_DAYS(NOW())", nativeQuery = true)
    public String getTodayMaxOutNumber();

    /**
     * 分组查询
     *
     * @param client
     * @return
     */
    @Query( value = "select name,count(*) from t_storage where clientname =?1  group by name" , nativeQuery = true)
    public List<Object[]> FindByGroup(String client);

    /**
     * 根据id设置仓位信息
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @Modifying
    @Query(value = "update t_storage set location_id = ?2 where id = ?1", nativeQuery = true)
    public void setLocation(Integer parseInt, Integer location);

    /**
     * 查询所有已经装车的商品
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @Query(value = "select * from t_storage where state like ?1", nativeQuery = true)
    public List<Storage> findByState(String state);

    /** 
    * @Description: 设置出库单号
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @Modifying
    @Query(value = "update t_storage set out_number = ?1 where id = ?2", nativeQuery = true)
    public void updateOutNumberById(String genCode, int parseInt);

    /**
     *根据出库日期获取
     */

    @Query(value = "SELECT * FROM t_storage WHERE delivery_time = ?1 AND state='装车' GROUP BY clientname, out_number", nativeQuery = true)
    public List<Storage> selectClientNameByOutDate(Date s);

    /***
     * 根据出库单号查询
     * @param outNumber
     * @return
     */
    @Query(value = "SELECT * FROM t_storage WHERE out_number =?1", nativeQuery = true)
    public List<Storage> selectOutByOutNumber(String outNumber);

    /***
     * 根据商品名称和出库单号查询数量
     * @param name
     * @param outNumber
     * @return
     */
    @Query(value = "SELECT COUNT(*) FROM t_storage WHERE NAME =?1 AND out_number = ?2", nativeQuery = true)
    public String selectCountByNameAndOutNumber(String name, String outNumber);


    /***
     *
     * g根据销售单号聚合查询
     * @param
     * @return
     */
    @Query( value = "select clientname,peasant,sale_number,name,model,length,price,realityweight,delivery_time,count(*) FROM t_storage WHERE state LIKE '%装车%' GROUP BY sale_list_product_id " , nativeQuery = true)
    public List<Object[]> FindBySaleListId();



    /*@Query( value = "select clientname,peasant,sale_number,name,model,length,price,realityweight,delivery_time,count(*) FROM t_storage GROUP BY sale_list_product_id" , nativeQuery = true)
    public List<Object[]> FindBySaleListIdOne();*/

    @Query( value = "select date_in_produced,clientname,sale_list,sale_list_product from t_storage group by sale_number",nativeQuery = true)
    public List<Storage> findSaleListNumber();

    /*    select a.accomplish_number,a.num,a.name,a.state,
    b.sale_date,b.sale_number,c.date_in_produced,c.clientname
     FROM t_sale_list_product
    AS a,t_sale_list AS b,t_storage AS c  GROUP BY sale_number
     */

    @Query (value = "select * from t_storage where sale_number = ?1 group by sale_list_product_id", nativeQuery = true)
    public List<Storage> findStorage(String saleNumber);

    /***
     * 根据id修改状态
     * @param parseInt
     * @param state
     */
    @Modifying
    @Query(value = "UPDATE t_storage SET state=?2 WHERE id = ?1", nativeQuery = true)
    public void updateByIdAndState(int parseInt, String state);

    /***
     * 根据状态查询
     * @param state
     * @return
     */
    @Query(value = "select * from t_storage where state = ?1", nativeQuery = true)
    List<Storage> selectByState(String state);
}
