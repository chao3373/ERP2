package com.shenke.controller.admin;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenke.entity.*;
import com.shenke.service.ClientService;
import com.shenke.service.LingShouService;
import com.shenke.service.SaleListService;
import com.shenke.service.StorageService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ClientService clientService;

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

    @RequestMapping("/add")
    public Map<String, Object> addd(LingShou lingShou, String lingshou, Double heji) {
        System.out.println(heji);
        String clientname = lingShou.getClientname();
        Client name = clientService.findName(clientname);
        if (name == null) {
            Client client = new Client();
            client.setName(clientname);
            clientService.save(client);
        }
        Client client = clientService.findName(clientname);
        System.out.println(lingShou);
        Date date = new Date();
        Map<String, Object> map = new HashMap<>();
        System.out.println(lingshou);
        Gson gson = new Gson();
        List<LingShou> lingShous = gson.fromJson(lingshou, new TypeToken<List<LingShou>>() {
        }.getType());
        for (LingShou lingShoul : lingShous) {
            lingShoul.setClientname(lingShou.getClientname());
            lingShoul.setDanhao(lingShou.getDanhao());
            lingShoul.setTel(lingShou.getTel());
            lingShoul.setDingjin(lingShou.getDingjin());
            lingShoul.setAddress(lingShou.getAddress());
            lingShoul.setXiaoshouDate(date);
        }
        System.out.println(lingShous);
        lingShouService.save(lingShous);
        SaleList saleList = new SaleList();
        saleList.setSaleNumber(lingShou.getDanhao());
        saleList.setSaleDate(date);
        if (heji != null) {
            saleList.setTotalPrice(heji);
        }
        saleList.setDingjin(lingShou.getDingjin());
        saleList.setClient(client);
        saleListService.saveOne(saleList);
        map.put("success", true);
        return map;
    }

    //添加销售商品
    @RequestMapping("/update")
    public Map<String, Object> add(LingShou lingShou, String goodsJson, String xiaoshouDatee) throws ParseException {
        System.out.println(lingShou);
        System.out.println(goodsJson);
        Map<String, Object> map = new HashMap<>();
        Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(xiaoshouDatee);
        Gson gson = new Gson();
//        List<SaleListProduct> saleListProducts = gson.fromJson(goodsJson, new TypeToken<List<SaleListProduct>>() {
//        }.getType());
        List<LingShou> lingShous = gson.fromJson(goodsJson, new TypeToken<List<LingShou>>() {
        }.getType());
        List<LingShou> lingShouList = new ArrayList<>();
        List<Storage> storages = new ArrayList<>();
        Map<Integer, Double> length = new HashMap<>();
        for (LingShou lingShou1 : lingShous) {
            Integer id = lingShou1.getStorageid();
            LingShou lingShou2 = lingShouService.findone(lingShou1.getId());
            lingShou2.setXiaoshouDate(date);
            lingShou2.setStorageid(id);
            lingShouList.add(lingShou2);
            if (length.get(id) != null) {
                length.put(id, length.get(id) + (lingShou1.getLength() * lingShou1.getNum()));
            } else {
                length.put(id, lingShou1.getLength() * lingShou1.getNum());
            }
//            storage.setShengyulength(storage.getShengyulength() - lingShou1.getLength());
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
        System.out.println(lingShouList);
        storageService.save(storages);
        System.out.println(storages);
        lingShouService.save(lingShouList);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/findbydanhao")
    public Map<String, Object> findbydanhao(String danhao) {
        Map<String, Object> map = new HashMap<>();
        List<LingShou> lingShous = lingShouService.findbydanhao(danhao);
        System.out.println(lingShous);
//        for (LingShou lingShou : lingShous) {
//            lingShou.setId(null);
//        }
        map.put("success", true);
        map.put("rows", lingShous);
        return map;
    }

    @RequestMapping("/find")
    public Map<String, Object> find(LingShou lingShou, String dateInProducedd, String dateInProduceddd) throws ParseException {
        System.out.println(lingShou);
        System.out.println(dateInProducedd);
        System.out.println(dateInProduceddd);
        if (StringUtil.isNotEmpty(dateInProducedd) && StringUtil.isNotEmpty(dateInProduceddd)) {
            lingShou.setStarDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd));
            lingShou.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProduceddd));
        }
        Map<String, Object> map = new HashMap<>();
        List<LingShou> lingShous = lingShouService.find(lingShou);
        System.out.println(lingShous);
        map.put("success", true);
        map.put("rows", lingShous);
        return map;
    }

    @RequestMapping("/updateShishou")
    public Map<String, Object> updateShishou(Integer id, Double shishou) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(id);
        System.out.println(shishou);
        lingShouService.updateShishou(id, shishou);
        map.put("success", true);
        return map;
    }
}
