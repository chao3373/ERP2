package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.LingShou;
import com.shenke.entity.SaleList;
import com.shenke.entity.SaleListProduct;
import com.shenke.entity.Storage;
import com.shenke.service.LingShouService;
import com.shenke.service.SaleListProductService;
import com.shenke.service.SaleListService;
import com.shenke.service.StorageService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/admin/LingShou")
public class LingShouAdminController {

    @Resource
    private LingShouService lingShouService;

    @Resource
    private StorageService storageService;

    @Resource
    private SaleListService saleListService;

    /**
     * 获取零售单号
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/genCode")
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("XS");
        code.append(DateUtil.getCurrentDateStr());
        String saleNumber = saleListService.getTodayMaxSaleNumber();
        if (saleNumber != null) {
            code.append(StringUtil.formatCode(saleNumber));
        } else {
            code.append("0001");
        }
        return code.toString();
    }

    /***
     * 查询可以零售的商品名称
     * @param s
     * @return
     */
    @RequestMapping("/lingshouList")
    public List<Storage> lingshouList(String s) {
        if (s == null) {
            s = "";
        }
        return lingShouService.lingshouList("%" + s + "%");
    }

    //添加销售商品
    @RequestMapping("/add")
    public Map<String, Object> add(LingShou lingShou, String goodsJson, String xiaoshouDatee) throws ParseException {
        System.out.println(lingShou);
        System.out.println(goodsJson);
        Map<String, Object> map = new HashMap<>();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(xiaoshouDatee);
        String danhao = lingShou.getDanhao();
        String clientname = lingShou.getClientname();
        Gson gson = new Gson();
        List<LingShou> lingShous = gson.fromJson(goodsJson, new TypeToken<List<LingShou>>() {
        }.getType());
        List<Storage> storages = new ArrayList<>();
        Map<Integer, Double> length = new HashMap<>();
        for (LingShou lingShou1 : lingShous) {
            Integer id = lingShou1.getId();
            if (length.get(id) != null) {
                length.put(id, length.get(id) + (lingShou1.getLength() * lingShou1.getNum()));
            } else {
                length.put(id, lingShou1.getLength() * lingShou1.getNum());
            }
//            storage.setShengyulength(storage.getShengyulength() - lingShou1.getLength());
            lingShou1.setStorageid(lingShou1.getId());
            lingShou1.setId(null);
            lingShou1.setDanhao(danhao);
            lingShou1.setClientname(clientname);
            lingShou1.setXiaoshouDate(date);
        }
        System.out.println(lingShous);
        for (Integer key : length.keySet()) {
            System.out.println("=======");
            System.out.println(key);
            Storage storage = storageService.findById(key);
            if (length.get(key) > storage.getShengyulength()) {
                map.put("success", false);
                map.put("msg", "编号为" + key + "的总长度超出剩余长度");
                return map;
            }
            Double shengyu = storage.getShengyulength() - length.get(key);
            System.out.println("剩余：" + shengyu);
            System.out.println(shengyu == 0);
            if (shengyu == 0) {
                System.out.println("等于0");
                storage.setState("出售");
            } else if (shengyu < 0) {
                map.put("success", false);
                map.put("msg", "未知错误！");
                return map;
            }
            storage.setShengyulength(shengyu);
            storages.add(storage);
        }
        System.out.println(storages);
        System.out.println(lingShous);
        storageService.save(storages);
        System.out.println(storages);
        lingShouService.save(lingShous);
        map.put("success", true);
        return map;
    }
}
