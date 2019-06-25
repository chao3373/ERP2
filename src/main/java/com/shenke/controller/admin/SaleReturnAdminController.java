package com.shenke.controller.admin;

import com.shenke.entity.SaleReturn;
import com.shenke.service.SaleReturnService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/saleReturn")
public class SaleReturnAdminController {

    @Resource
    private SaleReturnService saleReturnService;

    /***
     * 添加退货商品
     * @param saleReturn
     * @return
     */
    @RequestMapping("/add")
    public Map<String, Object> add(SaleReturn saleReturn) {
        Map<String, Object> map = new HashMap<>();
        saleReturnService.add(saleReturn);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/findAll")
    public List<SaleReturn> findAll(){
        return saleReturnService.findAll();
    }
}
