package com.shenke.controller.admin;

import com.shenke.entity.SeMu;
import com.shenke.service.SeMuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/semu")
public class SeMuAdminController {
    @Resource
    private SeMuService seMuService;

    @RequestMapping("/save")
    public Map<String, Object> save(SeMu seMu){
        Map<String, Object> map = new HashMap<>();
        seMuService.save(seMu);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(SeMu seMu, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<SeMu> SeMuList = seMuService.list(seMu, page, rows);
        Long total = seMuService.getCount(seMu);
        System.out.println(map);
        map.put("success", true);
        map.put("rows", SeMuList);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        seMuService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/semuList")
    public List<SeMu> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return seMuService.findByLikeName("%" + q + "%");
    }
}
