package com.shenke.repository;

import java.util.Date;
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
    @Query(value = "update t_storage set  state = ?1, delivery_time = ?3, out_number = ?4 where id = ?2", nativeQuery = true)
    public void updateStateById(String state, Integer id, Date date, String ck);

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
    @Query(value = "select * from t_storage where state like ?1", nativeQuery = true)
    List<Storage> selectByState(String state);

    /***
     * 修改成产人员
     * @param id
     * @param clerkName
     */
    @Modifying
    @Query(value = "update t_storage set clerk_name = ?2, clerk_id = ?3 where id = ?1", nativeQuery = true)
    void updateClerk(Integer id, String clerkName, Integer clerkId);

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

    @Query(value = "select * from t_storage where sale_number = ?1 and client_name = ?2", nativeQuery = true)
    List<Storage> findeBySaleNumberAndClient(String saleNumber, String client);

    /***
     * 修改班组
     * @param id
     * @param banzu
     * @param banzuid
     */
    @Modifying
    @Query(value = "update t_storage set group_name = ?2, group_id = ?3 where id = ?1", nativeQuery = true)
    void updatebanzu(Integer id, String banzu, Integer banzuid);

    /**
     * 根据id修改重量
     * @param id
     * @param zhongliang
     */
    @Modifying
    @Query(value = "update t_storage set realityweight = ?2 where id=?1", nativeQuery = true)
    void updatezhongliang(Integer id, Double zhongliang);

    /***
     * 根据id删除库存
     * @param id
     */
    @Modifying
    @Query(value = "delete from t_storage where id = ?1", nativeQuery = true)
    void deletekucun(Integer id);

    /***
     * 根据id修改长度
     * @param changdu
     * @param id
     */
    @Modifying
    @Query(value = "update t_storage set length = ?1 where id = ?2", nativeQuery = true)
    void updatechangdu(Integer changdu, Integer id);

    /***
     * 根据id修改厚度
     * @param houdu
     * @param id
     */
    @Modifying
    @Query(value = "update t_storage set price = ?1 where id = ?2", nativeQuery = true)
    void updatehoudu(String houdu, Integer id);

    //根据条件查询提货商品
    @Query(value = "select * from t_storage where serial_number = ?1 and state like '%提货%'", nativeQuery = true)
    List<Storage> selectTihuo(String pandianji);

    //根据条件查询提货商品
    @Query(value = "select * from t_storage where state like '%提货%'", nativeQuery = true)
    List<Storage> selectTihuo();

    //根据salelistproductid查询库存数量
    @Query(value = "select count(*) from t_storage where sale_list_product_id = ?1", nativeQuery = true)
    Integer findCountBySaleListProductId(Integer id);

    //根据salelistproductid查询
    @Query(value = "select * from t_storage where sale_list_product_id = ?1", nativeQuery = true)
    List<Storage> findBySaleListProductId(int id);

    //根据id修改时间
    @Modifying
    @Query(value = "update t_storage set date_in_produced = ?2 where id = ?1", nativeQuery = true)
    void updateshijian(Integer id, Date parse);
}
