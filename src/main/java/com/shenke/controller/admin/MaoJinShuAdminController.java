package com.shenke.controller.admin;

import com.shenke.entity.MaoJinShu;
import com.shenke.service.MaoJinShuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/maojinshu")
public class MaoJinShuAdminController {
    @Resource
    private MaoJinShuService maoJinShuService;

    @RequestMapping("/save")
    public Map<String, Object> save(MaoJinShu maoJinShu) {
        System.out.println(maoJinShu);
        Map<String, Object> map = new HashMap<>();
        maoJinShuService.save(maoJinShu);
        map.put("success", true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String, Object> list(MaoJinShu maoJinShu, Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        List<MaoJinShu> muLiaoList = maoJinShuService.list(maoJinShu, page, rows);
        Long total = maoJinShuService.getCount(maoJinShu);
        System.out.println(map);
        map.put("success", true);
        map.put("rows", muLiaoList);
        map.put("total", total);
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(Integer id){
        Map<String, Object> map = new HashMap<>();
        maoJinShuService.delete(id);
        map.put("success", true);
        return map;
    }

    /**
     * 下拉框模糊查询信息
     *
     * @return
     */
    @RequestMapping("/maojinshuList")
    public List<MaoJinShu> jitaiList(String q) {
        if (q == null) {
            q = "";
        }
        return maoJinShuService.findByLikeName("%" + q + "%");
    }

}
