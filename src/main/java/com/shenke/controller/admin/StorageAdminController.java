package com.shenke.controller.admin;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.shenke.entity.JieSuan;
import com.shenke.entity.Log;
import com.shenke.service.LogService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.service.StorageService;


/**
 * 入库单Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/storage")
public class StorageAdminController {

    @Resource
    private StorageService storageService;

    @Resource
    private LogService logService;

    /**
     * 入库
     *
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(Double weight, Integer saleListProductId, Integer jitaiProductionAllotId,
                                   Integer producionProcessId, Integer jitaiId, String clerkName, String group) {
        storageService.add(weight, saleListProductId, jitaiProductionAllotId, producionProcessId, jitaiId, clerkName, group);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
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
     * 查询所有未出库的商品
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


    /**
     * 获取出库单号
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/genCode")
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("AMCK");
        code.append(DateUtil.getCurrentDateStr());
        String saleNumber = storageService.getTodayMaxOutNumber();
        if (saleNumber != null) {
            code.append(StringUtil.formatCode(saleNumber));
        } else {
            code.append("00001");
        }
        return code.toString();
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
    public Map<String, Object> searchLiftMoney(String saleNumber, Integer location, Integer jitai, String productDate, Integer clerk, Integer group) {
        System.out.println(saleNumber + "=====" + location + "=====" + jitai + "=====" + productDate + "=====" + clerk + "=====" + group);
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("rows", storageService.searchLiftMoney(saleNumber, location, jitai, productDate, clerk, group));
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
}
