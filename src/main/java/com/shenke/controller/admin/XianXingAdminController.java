package com.shenke.controller.admin;

import com.shenke.entity.XianXing;
import com.shenke.service.XianXingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/xianxing")
public class XianXingAdminController {

    @Resource
    private XianXingService xianXingService;

    @RequestMapping("/save")
    public Map<String, Object> save(XianXing xianXing) {
        Map<String, Object> map = new HashMap<>();
        xianXingService.save(xianXing);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(XianXing xianXing, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<XianXing> xianXingList = xianXingService.list(xianXing, page, rows);
        Long total = xianXingService.getCount(xianXing);
        System.out.println(map);
        map.put("success", true);
        map.put("rows", xianXingList);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id) {
        Map<String, Object> map = new HashMap<>();
        xianXingService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/xianxingList")
    public List<XianXing> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return xianXingService.findByLikeName("%" + q + "%");
    }
}
