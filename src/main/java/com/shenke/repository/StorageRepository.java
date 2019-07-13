package com.shenke.repository;

import java.sql.Date;
import java.util.List;

import com.shenke.entity.StorageOut;
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
    @Query(value = "select * from t_storage where serial_number = ?1 and state = ?2", nativeQuery = true)
    public List<Storage> fandAllBySerialNumber(String serialNumber, String state);

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

    @Query(value = "select * from t_storage",nativeQuery = true)
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
    public void updateStateById(String state, Integer id, java.util.Date date);

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
    @Query(value = "SELECT clientname, peasant, name, color, out_number, model, price, length, realityweight as weight, sum(realityweight) as sumweight, count(*) as sumnum, delivery_time, dabaonum FROM t_storage WHERE out_number=? GROUP BY sale_list_product_id, name, model, length, color, realityweight", nativeQuery = true)
    public List<Object[]> selectOutByOutNumber(String outNumber);

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
    @Query(value = "select * from t_storage where state like ?1", nativeQuery = true)
    List<Storage> selectByState(String state);

    /***
     * 修改库存信息
     * @param id
     * @param oneWeight
     * @param shiji
     * @param length
     */
    @Modifying
    @Query(value = "update t_storage set length=?4, oneweight=?2, realityweight=?3 where id=?1", nativeQuery = true)
    void editKuCun(Integer id, Integer oneWeight, Double shiji, Double length);

    /***
     * 查询上月结转
     * @param date1
     * @return
     */
    @Query(value = "select (SELECT SUM(realityweight) FROM t_storage WHERE date_in_produced < ?1)-(SELECT SUM(realityweight)  FROM t_storage WHERE delivery_time < ?1)", nativeQuery = true)
    Integer lastMonth(java.util.Date date1);

    /***
     * 查询本月入库
     * @param udate
     * @param endate
     * @return
     */
    @Query(value = "SELECT SUM(realityweight) FROM t_storage WHERE date_in_produced >= ?1 AND date_in_produced < ?2", nativeQuery = true)
    Integer monthIn(java.util.Date udate, java.util.Date endate);

    /***
     * 查询本月出库
     * @param udate
     * @param endate
     * @return
     */
    @Query(value = "SELECT SUM(realityweight) FROM t_storage WHERE delivery_time >= ?1 AND delivery_time < ?2", nativeQuery = true)
    Integer monthOut(java.util.Date udate, java.util.Date endate);
}
