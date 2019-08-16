package com.shenke.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.shenke.entity.*;

/**
 * 入库单Service
 *
 * @author Administrator
 */
public interface StorageService {

    /**
     * 添加入库单
     *
     */
    public void add(Storage storage, String clerkName, String groupName);
    public void add(Storage storage, String clerkName, String groupName, Double changdu);

    /**
     * 非标入库单
     *
     */
    public void feibiaoAdd(Storage storage, String clerkName, String groupName);

    public List<Storage> fandAll();

    /**
     * 根据机器的序列号查询
     *
     * @return
     */
    public List<Storage> fandAllBySerialNumber(String serialNumber, String state);

    /**
     * 根据订单销售商品id查询入库单
     *
     * @param
     */
    public Storage selectByMaxId();

    /**
     * 改为已出库
     */
    public void outStorage(int id, Date date);

    /**
     * 查询未出库的信息
     *
     * @return
     */
    public List<Storage> outSuccess();


    /**
     *
     */
    public void gai(String storage_number, int id);

    /**
     * 根据id修改状态
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public void updateStateById(String state, Integer id, Date date, String ck);

    /**
     * 根据客户查询并按照产品名称排序
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public List<Object[]> findByClientAndGroupByName(String client);

    /**
     * 查询所有已经完成的商品
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public List<Storage> findAll();

    /**
     * 根据id模糊查询
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public Storage findById(Integer id);

    /**
     * 分组查询
     * @param client
     * @return
     */
    public List<JieSuan> FindByGroup(String client);



    public void setLocation(Integer parseInt, Integer location);

    public void save(Storage storage);

    public void save(Storage storage, Integer num);

    public List<Storage> findByState(String state);

    public List<Storage> detail(Map<String, Object> map1);

    public List<Storage> selectClientNameByOutDate(Date s);

    public List<Storage> selectOutByOutNumber(String outNumber);

    public String selectCountByNameAndOutNumber(String name, String outNumber);

    public List<Count> FindBySaleListId();

    Integer countBySaleListProductId(Integer id, Storage storage);

    public List<Storage> findSaleListNumber();

    public List<Storage> findStorage(String saleNumber);

    List<Storage> searchLiftMoney(Map<String, Object> map);

    public List<Storage> JitaiProduct(Storage storage, java.util.Date date, java.util.Date star, java.util.Date end);

    public List<Storage> KucunSearch(Map<String, Object> map);


    List<Storage> select(Storage storage, String dateInProducedd);

    void updateByIdAndState(int parseInt, String state);

    List<Storage> selectByState(String state);

    void updateOutNumberById(int parseInt, String ck);

    String genCode() throws Exception;

    void updateClerk(Integer id, String clerkName, Integer clerkId);

    void editKuCun(Integer id, Integer oneWeight, Double shiji, Double length);

    List<Storage> findeBySaleNumberAndClient(String saleNumber, String client);

    Month selectMonth(String month, String year);

    Month selectYear(String year);

    void updatebanzu(Integer id, String banzu, Integer banzuid);

    void updatezhongliang(Integer id, Double zhongliang);

    String deletekucun(Integer id);

    void updatechangdu(Integer changdu, Integer id);

    void updatehoudu(String houdu, Integer id);

    List<Storage> selectTihuo(String pandianji);

    Integer findCountBySaleListProductId(Integer id);
}
