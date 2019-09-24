package com.shenke.controller.admin;

import com.shenke.entity.QiTa;
import com.shenke.service.QiTaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/qita")
public class QiTaAdminController {
    @Resource
    private QiTaService qiTaService;

    @RequestMapping("/save")
    public Map<String, Object> save(QiTa qiTa){
        Map<String, Object> map = new HashMap<>();
        qiTaService.save(qiTa);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(QiTa qiTa, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<QiTa> xianXingList = qiTaService.list(qiTa, page, rows);
        Long total = qiTaService.getCount(qiTa);
        System.out.println(map);
        map.put("success", true);
        map.put("rows", xianXingList);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        qiTaService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/qitaList")
    public List<QiTa> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return qiTaService.findByLikeName("%" + q + "%");
    }
}
