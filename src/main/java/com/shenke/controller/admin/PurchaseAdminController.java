package com.shenke.controller.admin;

import com.shenke.entity.Log;
import com.shenke.entity.Plant;
import com.shenke.entity.Purchase;
import com.shenke.service.LogService;
import com.shenke.service.PurchaseService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 采购单Controller
 * @Param:
 * @return:
 * @Author: Andy
 * @Date:
 */
@RestController
@RequestMapping("/admin/purchase")
public class PurchaseAdminController {

    @Resource
    private PurchaseService purchaseService;

    @Resource
    private LogService logService;

    /**
     *保存采购单
     */
    @RequestMapping("/save")
    public Map<String, Object> save(Integer plant,String PurchaseNumber,String principal,
                                    String PurchasingAgent,String purchaseDate,String outDate,String carNumber,
                                    Double tonnage,String carrier,Double sumMoney,Double sumWeight,
                                    String remark,String goodsJson) throws Exception {
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


        //数据封装
        Purchase purchase = new Purchase();

        Plant plant1 = new Plant();
        plant1.setId(plant);
        purchase.setPurchaseNumber(PurchaseNumber);
        purchase.setPrincipal(principal);
        purchase.setPurchasingAgent(PurchasingAgent);

        //purchase.setPurchaseDate(Date purchaseDate);
        // purchase.setOutDate(outDate outDate);
        purchase.setCarNumber(carNumber);
        purchase.setTonnage(tonnage);
        purchase.setCarrier(carrier);
        purchase.setSumMoney(sumMoney);
        purchase.setSumWeight(sumWeight);
        purchase.setRemark(remark);

        purchaseService.save(purchase);
        map.put("success",true);
        return map;
    }

    /**
     * 获取进货单号
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/genCode")
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("CG");
        code.append(DateUtil.getCurrentDateStr());
        String purchaseNumber = purchaseService.getTodayMaxPurchaseNumber();
        if (purchaseNumber != null) {
            code.append(StringUtil.formatCode(purchaseNumber));
        } else {
            code.append("0001");
        }
        return code.toString();
    }
}
