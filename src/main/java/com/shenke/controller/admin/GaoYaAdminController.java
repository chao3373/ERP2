package com.shenke.controller.admin;

import com.shenke.entity.GaoYa;
import com.shenke.service.GaoyaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/gaoya")
public class GaoYaAdminController {

    @Resource
    private GaoyaService gaoyaService;

    @RequestMapping("/save")
    public Map<String, Object> save(GaoYa gaoya){
        Map<String, Object> map = new HashMap<>();
        gaoyaService.save(gaoya);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(GaoYa gaoya, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<GaoYa> gaoyaList = gaoyaService.list(gaoya, page, rows);
        Long total = gaoyaService.getCount(gaoya);
        System.out.println(map);
        map.put("success", true);
        map.put("rows", gaoyaList);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        gaoyaService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/gaoyaList")
    public List<GaoYa> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return gaoyaService.findByLikeName("%" + q + "%");
    }
}
