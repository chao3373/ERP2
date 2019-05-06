package com.shenke.controller.admin;

import com.shenke.service.PurchaseService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
