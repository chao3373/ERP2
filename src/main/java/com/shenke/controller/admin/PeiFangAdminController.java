package com.shenke.controller.admin;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.shenke.entity.PeiFang;
import com.shenke.service.PeiFangService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/peifang")
public class PeiFangAdminController {
    @Resource
    private PeiFangService peiFangService;

    @RequestMapping("/tree")
    public String findAll(){
        JSONArray array = JSONUtil.createArray();
        List<PeiFang> all = peiFangService.findAll();
        for(PeiFang peiFang : all){
            JSONObject obj = JSONUtil.createObj();
            obj.append("id", peiFang.getId());
            obj.append("text", peiFang.getName());
            array.add(obj);
        }
        return array.toString();
    }

    @RequestMapping("/add")
    public Map<String, Object> add(PeiFang peiFang){
        Map<String, Object> map = new HashMap<>();
        if (peiFangService.add(peiFang)){
            map.put("success", true);
        }else {
            map.put("success", false);
            map.put("msg", "改配方已存在！");
        }
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        peiFangService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/peifangList")
    public List<PeiFang> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return peiFangService.findByName("%" + q + "%");
    }


}
