package com.shenke.controller.admin;

import java.util.*;
import javax.annotation.Resource;

import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shenke.entity.Log;
import com.shenke.entity.SaleListProduct;
import com.shenke.service.ClientService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListProductService;

@RestController
@RequestMapping("/admin/saleListProduct")
public class SaleListProductAdminController {

    @Resource
    private LogService logService;

    @Resource
    private ClientService clientService;

    @Resource
    private SaleListProductService saleListProductService;

    /**
     * 订单审核通过
     *
     * @return
     */
    @RequestMapping("/passTheAudit")
    public Map<String, Object> passTheAudit(String ids) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            int id = Integer.parseInt(idArr[i]);

            logService.save(new Log(Log.AUDIT_ACTION, "审核通过"));
            saleListProductService.passTheAudit(id);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 订单审核失败
     *
     * @param ids
     * @return
     */
    @RequestMapping("/auditFailure")
    public Map<String, Object> auditFailure(String ids, String cause) {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] idArr = ids.split(",");
        for (int i = 0; i < idArr.length; i++) {
            int id = Integer.parseInt(idArr[i]);
            logService.save(new Log(Log.AUDIT_ACTION, "审核失败"));
            saleListProductService.auditFailure(id, cause);
        }
        map.put("success", true);
        return map;
    }

    /**
     * 查询所有审核成功的销售商品信息
     *
     * @return
     */
    @RequestMapping("/listProductSucceed")
    public Map<String, Object> listProductSucceed() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", saleListProductService.listProductSucceed());
        return map;
    }

    /**
     * 下拉框模糊查询所有幅宽
     *
     * @return
     */
    @RequestMapping("/breadthList")
    public List<SaleListProduct> breadthList(String q) {
        if (q == null) {
            q = "";
        }
        return saleListProductService.breadthList(q);
    }

    /**
     * 根据条件查询所有订单商品信息
     *
     * @param modeSort
     * @param priceSort
     * @param lengthSort
     * @param client
     * @return
     */
    @RequestMapping("/screen")
    public Map<String, Object> screen(String modeSort, String priceSort, String lengthSort, String client, String realityprice,
                                      String oneweight, String sumwight, String realitymodel) {

        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> condition = new HashMap<String, Object>();

        condition.put("modeSort", modeSort);
        condition.put("priceSort", priceSort);
        condition.put("lengthSort", lengthSort);
        condition.put("client", client);
        condition.put("realityprice", realityprice);
        condition.put("oneweight", oneweight);
        condition.put("sumwight", sumwight);
        condition.put("realitymodel", realitymodel);


        List<SaleListProduct> screen = saleListProductService.screen(condition);
        map.put("success", true);
        map.put("rows", screen);
        return map;
    }

    /**
     * 根据机台id，下发状态，通知单号查询
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @RequestMapping("/selectByJitaiIdAndIssueStateAndInformNumber")
    public Map<String, Object> selectByJitaiIdAndIssueStateAndInformNumber(Integer jitaiId, String state, String informNumber) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtil.isNotEmpty(state)) {
            state = "%" + state + "%";
        } else {
            state = "%";
        }
        Long infLong = null;
        if (StringUtil.isNotEmpty(informNumber)) {
            infLong = Long.parseLong(informNumber);
        }
        map.put("success", true);
        map.put("rows", saleListProductService.selectByJitaiIdAndIssueStateAndInformNumber(jitaiId, state, infLong));
        return map;
    }

    /**
     * 查询该机台没有所有未完成的生产通知单号
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    @RequestMapping("/selectNoAccomplish")
    public Map<String, Object> selectNoAccomplish(Integer jitaiId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        List<SaleListProduct> saleListProducts = saleListProductService.selectNoAccomplish(jitaiId);
        Set<Long> informNumber = new HashSet<Long>();
        for (SaleListProduct saleListProduct : saleListProducts) {
            informNumber.add(saleListProduct.getInformNumber());
        }
        map.put("rows", informNumber);
        return map;
    }

    /**
     * 根据订单状态查询订单
     */
    @RequestMapping("/listProductByState")
    public Map<String, Object> listProductByState(String state) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", true);
        map.put("rows", saleListProductService.listProductByState(state));
        return map;
    }



    /**
     * 添加或修改仓库信息
     */
    @RequestMapping("/saveInfo")
    public Map<String, Object> save(SaleListProduct saleListProduct) {
        System.out.println("****************************************");
        System.out.println(saleListProduct);
        System.out.println("controller");
        Map<String, Object> map = new HashMap<>();
        saleListProductService.save(saleListProduct);
        map.put("success", true);
        return map;
    }

    /***
     * 修改机台
     */
    @RequestMapping("/alertJitai")
    public Map<String, Object> alertJitai(String idsStr, Integer jitai) {
        Map<String, Object> map = new HashMap<>();
        String[] ids = idsStr.split(",");
        for (int i = 0; i < ids.length; i++) {
            saleListProductService.updateJitaiId(Integer.parseInt(ids[i]), jitai);
        }
        map.put("success", true);
        return map;
    }

    /***
     * 下发机台
     * @return
     */
    @RequestMapping("/issue")
    public Map<String, Object> issue(String idStr){
        Map<String, Object> map = new HashMap<>();
        String[] ids = idStr.split(",");
        for (int i = 0; i < ids.length; i++) {
            SaleListProduct saleListProduct = saleListProductService.findById(Integer.parseInt(ids[i]));
            saleListProductService.updateState("下发机台：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
            saleListProductService.updateIussueState("下发机台：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
        }
        map.put("success", true);
        return map;
    }


    @RequestMapping("/findAll")
    public Map<String,Object> findAll(){
        Map<String,Object> map = new HashMap<>();
        List<SaleListProduct> list = saleListProductService.fandAll();
        map.put("rows",list);
        System.out.println(map);
        return  map;
    }

    @RequestMapping("/findJitaiProduct")
    public Map<String,Object> findJitaiProduct(){
        Map<String,Object> map = new HashMap<>();
        List<SaleListProduct> list = saleListProductService.findJitaiProduct();
        map.put("rows",list);
        System.out.println(list);
        return map;
    }

    @RequestMapping("/searchJitai")
    public Map<String, Object> searchLiftMoney(String saleNumber, Integer jitai, String saleDate,String deliveryDate,String allorState,String state) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();

        map1.put("saleNumber", saleNumber);
        map1.put("jitai", jitai);
        map1.put("saleDate", saleDate);
        map1.put("allorState",allorState);
        map1.put("deliveryDate",deliveryDate);
        map1.put("state",state);


        map.put("success", true);
        map.put("rows", saleListProductService.searchJitai(map1));

        System.out.println(map);

        return map;
    }

    /**
     * 修改完成数量
     * @param id
     */
    @RequestMapping("/updateAccomplishNumber")
    public Map<String, Object> update(Integer id){
        Map<String, Object> map = new HashMap<>();
        saleListProductService.updateAccomplishNumber(id);
        map.put("success", true);
        return map;
    }
}
