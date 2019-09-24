package com.shenke.controller.admin;

import com.shenke.entity.PeiFangInfo;
import com.shenke.service.PeiFangInfoService;
import com.shenke.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/peifanginfo")
public class PeiFangInfoAdminController {
    @Resource
    private PeiFangInfoService peiFangInfoService;

    @RequestMapping("/list")
    private Map<String, Object> list(PeiFangInfo peiFangInfo) {
        System.out.println(peiFangInfo);
        Map<String, Object> map = new HashMap<>();
        List<PeiFangInfo> peiFangInfoList = peiFangInfoService.list(peiFangInfo);
        map.put("success", true);
        map.put("rows", peiFangInfoList);
        return map;
    }

    @RequestMapping("/findByPeiFangId")
    private Map<String, Object> findByPeiFangId(Integer id) {
        Map<String, Object> map = new HashMap<>();
        List<PeiFangInfo> peiFangInfos = peiFangInfoService.findByPeiFangId(id);
        for (PeiFangInfo peiFangInfo : peiFangInfos) {
            System.out.println(peiFangInfo);
            System.out.println(peiFangInfo.getQitaname());
            peiFangInfo.setMuliaoinfo(peiFangInfo.getMuliaoname() + "%" + peiFangInfo.getMuliaonum() * 100);
            peiFangInfo.setXianxinginfo(peiFangInfo.getXianxingname() + "%" + peiFangInfo.getXianxingnum() * 100);
            peiFangInfo.setGaoyainfo(peiFangInfo.getGaoyaname() + "%" + peiFangInfo.getGaoyanum() * 100);
            peiFangInfo.setMaojinshuinfo(peiFangInfo.getMaojinshuname() + "%" + peiFangInfo.getMaojinshunum() * 100);
            peiFangInfo.setSemuinfo(peiFangInfo.getSemuname() + "%" + peiFangInfo.getSemunum() * 100);
            if (StringUtil.isNotEmpty(peiFangInfo.getQitaname())){
                peiFangInfo.setQitainfo(peiFangInfo.getQitaname() + "%" + peiFangInfo.getQitanum() * 100);
            }
        }
        map.put("success", true);
        map.put("rows", peiFangInfos);
        return map;
    }

    @RequestMapping("/save")
    private Map<String, Object> save(PeiFangInfo peiFangInfo) {
        System.out.println(peiFangInfo);
        Map<String, Object> map = new HashMap<>();
        PeiFangInfo peiFangInfo1 = peiFangInfoService.findByCengAndPeiFangId(peiFangInfo.getCeng(), peiFangInfo.getPeiFang().getId());
        if (peiFangInfo1 != null) {
            map.put("success", false);
            map.put("msg", "该配方已经添加" + peiFangInfo.getCeng());
            return map;
        } else {
            double num;
            if (peiFangInfo.getQitanum() != null) {
                num = peiFangInfo.getMuliaonum() + peiFangInfo.getXianxingnum() + peiFangInfo.getGaoyanum() + peiFangInfo.getMaojinshunum() + peiFangInfo.getSemunum() + peiFangInfo.getQitanum();
            } else {
                num = peiFangInfo.getMuliaonum() + peiFangInfo.getXianxingnum() + peiFangInfo.getGaoyanum() + peiFangInfo.getMaojinshunum() + peiFangInfo.getSemunum();
            }
            System.out.println(num);
            if (num != 1) {
                map.put("success", false);
                map.put("msg", "所有比例相加不等于1");
                return map;
            } else {
                peiFangInfoService.save(peiFangInfo);
                map.put("success", true);
                return map;
            }
        }
    }
}
