package com.shenke.controller.admin;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.Oneway;

import com.shenke.entity.*;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.ClerkService;
import com.shenke.service.LogService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.service.StorageService;


/**
 * 入库单Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/storage/")
public class StorageAdminController {

    @Resource
    private StorageService storageService;

    @Resource
    private ClerkService clerkService;

    @Resource
    private LogService logService;

    @Resource
    private SaleListProductRepository saleListProductRepository;

    /**
     * 入库
     *
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(Storage storage, String clerkName, String groupName) {
        System.out.println(storage);
        System.out.println("员工名称：" + clerkName);
        storageService.add(storage, clerkName, groupName);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer id = storageService.selectByMaxId().getId();
        map.put("success", true);
        map.put("id", id);
        return map;
    }

    /**
     * 非标入库
     *
     * @return
     */
    @RequestMapping("/feibiaoAdd")
    public Map<String, Object> feibiaoAdd(Storage storage, String clerkName, String groupName) {
        storageService.feibiaoAdd(storage, clerkName, groupName);
        Map<String, Object> map = new HashMap<String, Object>();
        Integer id = storageService.selectByMaxId().getId();
        map.put("success", true);
        map.put("id", id);
        return map;
    }

    /**
     * 根据订单销售商品id查询入库单
     *
     * @return
     */
    @RequestMapping("/selectByMaxId")
    public Map<String, Object> selectByMaxId() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("row", storageService.selectByMaxId());
        return map;
    }

    /**
     * 数据库改为已出库
     *
     * @param ids
     * @return
     */

    @RequestMapping("/out")
    public Map<String, Object> outStorage(String ids) {
        Map<String, Object> map = new HashMap<>();
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            int id = Integer.parseInt(idArr[i]);
            logService.save(new Log(Log.AUDIT_ACTION, "准备出库"));
            storageService.outStorage(id, new Date(System.currentTimeMillis()));
        }
        map.put("success", true);
        return map;
    }

    /**
     * 手动出库
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/outKu")
    public Map<String, Object> out(String ids) {
        Map<String, Object> map = new HashMap<>();
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            storageService.updateStateById("装车", Integer.parseInt(idArr[i]), new Date(System.currentTimeMillis()));
        }
        map.put("success", true);
        return map;
    }

    /**
     * 查询所有的商品
     */
    @RequestMapping("/outSuccess")
    public Map<String, Object> outSuccess() {
        Map<String, Object> map = new HashMap<>();
        map.put("rows", storageService.outSuccess());
        return map;
    }


    @RequestMapping("/save")
    public Map<String, Object> gai(String pandianji, String ids) {
        Map<String, Object> map = new HashMap<>();
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            int id = Integer.parseInt(idArr[i]);
            logService.save(new Log(Log.AUDIT_ACTION, "准备出库"));
            storageService.gai(pandianji, id);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 根据客户名称查询并按产品名称分组
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/findByClientAndGroupByName")
    public Map<String, Object> findByClientAndGroupByName(String client) {
        System.out.println(client);
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object[]> byClientAndGroupByName = storageService.findByClientAndGroupByName(client);
        map.put("success", true);
        map.put("data", byClientAndGroupByName);
        return map;
    }

    /**
     * 根据id查询
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/findById")
    public Map<String, Object> findById(Integer id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", storageService.findById(id));
        return map;
    }

    @RequestMapping("/findbygroup")
    public Map<String, Object> FindByGroup(String client) {
        Map<String, Object> map = new HashMap<>();
        List<JieSuan> findbygroup = storageService.FindByGroup(client);
        map.put("success", true);
        map.put("data", findbygroup);

        return map;
    }

    /**
     * 根据条件查询提货状态的订单
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/searchLiftMoney")
    public Map<String, Object> searchLiftMoney(String saleNumber, String name, String client, String mode, String price, String realityweight, String productDate, String pleasant) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();

        map1.put("saleNumber", saleNumber);
        map1.put("pleasant", pleasant);
        map1.put("productDate", productDate);
        map1.put("realityweight", realityweight);
        map1.put("name", name);
        map1.put("client", client);
        map1.put("mode", mode);
        map1.put("price", price);
        map.put("success", true);
        map.put("rows", storageService.searchLiftMoney(map1));
        return map;
    }

    /**
     * 根据id设置仓位信息
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/setLocation")
    public Map<String, Object> setLocation(String ids, Integer location) {
        Map<String, Object> map = new HashMap<>();
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            storageService.setLocation(Integer.parseInt(split[i]), location);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 手动添加库存信息
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/saveAdd")
    public Map<String, Object> saveAdd(Storage storage) {
        System.out.println(storage);
        System.out.println(storage.getNum());
        storageService.save(storage, storage.getNum());
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        return map;
    }

    /**
     * 查询所有已经装车的商品
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/truck")
    public Map<String, Object> truck(String state) {
        Map<String, Object> map = new HashMap<>();
        if (state == null) {
            state = "%%";
        } else {
            state = "%" + state + "%";
        }
        map.put("rows", storageService.findByState(state));
        return map;
    }

    /**
     * 按条件查询出库明细表
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/detail")
    public Map<String, Object> detail(String date, String client, String peasant, String product, String order) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        if (StringUtil.isNotEmpty(date)) {
            map1.put("date", new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } else {
            map1.put("date", null);
        }
        map1.put("client", client);
        map1.put("peasant", peasant);
        map1.put("product", product);
        map1.put("order", order);
        map.put("success", true);
        List<Storage> storageList = storageService.detail(map1);
        for (Storage storage : storageList) {
            storage.setSum(storageService.countBySaleListProductId(storage.getSaleListProduct().getId()));
        }
        map.put("rows", storageList);
        return map;
    }

    /**
     * 根据出库时间获取客户名称
     */
    @RequestMapping("/selectClientNameByOutDate")
    public List<Storage> selectClientNameByOutDate(String outDate) throws ParseException {
        return storageService.selectClientNameByOutDate(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(outDate).getTime()));
    }

    /**
     * 根据出库单号查询
     */
    @RequestMapping("/selectOutByOutNumber")
    public List<Storage> selectOutByOutNumber(String outNumber) {
        System.out.println(outNumber);
        return storageService.selectOutByOutNumber(outNumber);
    }

    /***
     * 根据商品名称和出库单号查询数量
     * @param name
     * @param outNumber
     * @return
     */
    @RequestMapping("/selectCountByNameAndOutNumber")
    public String selectCountByNameAndOutNumber(String name, String outNumber) {
        return storageService.selectCountByNameAndOutNumber(name, outNumber);
    }

    /***
     * 根据Salelist
     * @param
     * @return
     */
    @RequestMapping("/findbySalelistId")
    public Map<String, Object> FindBySaleListId() {
        Map<String, Object> map = new HashMap<>();
        List<Count> findBySaleListId = storageService.FindBySaleListId();
        map.put("success", true);
        map.put("rows", findBySaleListId);

        System.out.println(map);
        return map;
    }

    /***
     * 查询所有的库存
     * @return
     */
    @RequestMapping("/findAll")
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap<>();
        List<Storage> list = storageService.findAll();
        map.put("rows", list);
        return map;
    }

    @RequestMapping("/findSaleListNumber1")
    public Map<String, Object> findSaleListNumber() {
        Map<String, Object> map = new HashMap<>();
        List<Storage> list = storageService.findSaleListNumber();
        map.put("rows", list);
        return map;
    }

    @RequestMapping("/findSaleListId")
    public Map<String, Object> findStorage(String saleNumber) {
        Map<String, Object> map = new HashMap<>();
        List<Storage> list1 = storageService.findStorage(saleNumber);
        map.put("rows", list1);
        return map;
    }

    /***
     * 查询每个班组的生产产量
     * @param
     * @param
     * @param
     * @return
     */
    @RequestMapping("/JitaiProduct")
    public Map<String, Object> JitaiProduct(Storage storage, String dateInProducedd) {
        System.out.println(storage);
        System.out.println(dateInProducedd);
        Map<String, Object> map = new HashMap<>();
        System.out.println(storage.getGroupName());
        if (StringUtil.isNotEmpty(storage.getGroupName()) && storage.getGroupName().equals("夜班")) {
            String star = dateInProducedd + " 17:00:00";
            String end = dateInProducedd.split("-")[0] + "-" + dateInProducedd.split("-")[1] + "-" + (Integer.parseInt(dateInProducedd.split("-")[2]) + 1) + " 14:00:00";
            try {
                java.util.Date stard = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(star);
                java.util.Date endd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
                System.out.println("夜班");
                System.out.println(stard);
                System.out.println(endd);
                map.put("rows", storageService.JitaiProduct(storage, null, stard, endd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            try {
                java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 00:00:00");
                String end = dateInProducedd + " 23:59:59";
                java.util.Date endd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end);
                map.put("rows", storageService.JitaiProduct(storage, date, date, endd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        map.put("success", true);
        return map;
    }

    @RequestMapping("/kucunzonglan")
    public Map<String, Object> KuCunZongLan(String clientname, String saleNumber, String saleDate) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("clientname", clientname);
        map1.put("saleNumber", saleNumber);
        map1.put("saleDate", saleDate);

        map.put("success", true);
        map.put("rows", storageService.KucunSearch(map1));
        return map;

    }

    /***
     * 当前库存查询
     * @return
     */
    @RequestMapping("/select")
    public Map<String, Object> select(Storage storage, String dateInProducedd) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(storage);
        List<Storage> list = storageService.select(storage, dateInProducedd);
        map.put("success", true);
        map.put("rows", list);
        return map;
    }

    /***
     *
     * 根据ids和状态设置状态
     * @param idArr
     * @param state
     * @return
     */
    @RequestMapping("/updateByIdsAndState")
    public Map<String, Object> updateByIdAndState(String idArr, String state) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < idArr.split(",").length; i++) {
            storageService.updateByIdAndState(Integer.parseInt(idArr.split(",")[i]), state);
        }
        map.put("success", true);
        return map;
    }

    /***
     * 根据状态查询
     * @param state
     * @return
     */
    @RequestMapping("/selectByState")
    public Map<String, Object> selectByState(String state) {
        System.out.println(state);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("rows", storageService.selectByState(state));
        return map;
    }

    @RequestMapping("/selectState")
    public Map<String, Object> selectState(String state){
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("rows", storageService.selectByState(state));
        return map;
    }

    /***
     * 选中的商品回滚到仓库
     * @param ids
     * @return
     */
    @RequestMapping("/goBackku")
    public Map<String, Object> goBackku(String ids) {
        Map<String, Object> map = new HashMap<>();
        String[] split = ids.split(",");
        for (int i = 0; i < split.length; i++) {
            storageService.updateByIdAndState(Integer.parseInt(split[i]), "生产完成" + storageService.findById(Integer.parseInt(split[i])).getJiTaiName());
        }
        map.put("success", true);
        return map;
    }

    /***
     * 查询所有生产完成的
     * @return
     */
    @RequestMapping("/allWanCheng")
    public Map<String, Object> allWanCheng() {
        Map<String, Object> map = new HashMap<>();
        List<Storage> byState = storageService.findByState("%生产完成%");
        map.put("success", true);
        map.put("rows", byState);
        return map;
    }

    /***
     * 修改生产员工
     * @param ids
     * @param clerkName
     * @return
     */
    @RequestMapping("/updateClerk")
    public Map<String, Object> updateClerk(Integer[] ids, String clerkName){
        System.out.println(ids);
        System.out.println(clerkName);
        Integer clerkId= clerkService.finName(clerkName).getId();
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < ids.length; i++) {
            System.out.println(clerkName);;
            storageService.updateClerk(ids[i], clerkName, clerkId);
        }

        map.put("success", true);
        return map;
    }
}
