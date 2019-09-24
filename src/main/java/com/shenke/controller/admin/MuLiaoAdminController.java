package com.shenke.controller.admin;

import com.shenke.entity.MuLiao;
import com.shenke.service.MuLiaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/muliao")
public class MuLiaoAdminController {

    @Resource
    private MuLiaoService muLiaoService;

    @RequestMapping("/save")
    public Map<String, Object> save(MuLiao muLiao) {
        System.out.println(muLiao);
        Map<String, Object> map = new HashMap<>();
        muLiaoService.save(muLiao);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(MuLiao muLiao, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<MuLiao> muLiaoList = muLiaoService.list(muLiao, page, rows);
        Long total = muLiaoService.getCount(muLiao);
        System.out.println(map);
        map.put("success", true);
        map.put("rows", muLiaoList);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        muLiaoService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/muliaoList")
    public List<MuLiao> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return muLiaoService.findByLikeName("%" + q + "%");
    }
}
