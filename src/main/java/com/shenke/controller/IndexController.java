package com.shenke.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;
import com.shenke.entity.Product;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.ProductService;
import com.shenke.util.DaochuUtil;
import com.shenke.util.QRCode;
import com.shenke.util.ReadExcelUtil;
import com.shenke.util.StringUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.shenke.entity.Storage;
import com.shenke.service.StorageService;
import com.shenke.service.UserService;

@Controller
public class IndexController {

    @Resource
    private UserService userService;

    @Resource
    private StorageService storageService;

    @Resource
    private SaleListProductRepository saleListProductRepository;

    @Resource
    private ProductService productService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/login.html";
    }

    @RequestMapping("/import")
    public String port() {
        return "redirect:/indexx.html";
    }

    @ResponseBody
    @RequestMapping("/static/denglu")
    public Map<String, Object> denglu(String name, String psw, String serialNumber) {
        System.out.println(serialNumber);
        Map<String, Object> map = new HashMap<String, Object>();
        if (userService.findNamePsw(name, psw) == null) {
            map.put("errorInfo", "用户名或密码错误");

            return map;
        }
        List<Storage> list = storageService.fandAllBySerialNumber(serialNumber);
        if (list == null || list.size() == 0) {
            map.put("errorInfo", "没有关于本机的记录");
        } else {
            map.put("rows", list);
        }
        return map;
    }

    @ResponseBody
    @RequestMapping("/static/shangchuan")
    public String shangchuan(HttpServletRequest request, HttpServletResponse response, String serialNumber) throws Exception {
        System.out.println("上传");
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println(wholeStr);
        String[] split = wholeStr.split(",");
        for (int i = 0; i < split.length; i++) {
            if (StringUtil.isNotEmpty(split[i])) {
                storageService.updateStateById("装车", Integer.parseInt(split[i]), new Date(System.currentTimeMillis()));
                storageService.updateOutNumberById(Integer.parseInt(split[i]));
                saleListProductRepository.updateState("装车：" + storageService.findById(Integer.parseInt(split[i])).getJiTai().getName(), storageService.findById(Integer.parseInt(split[i])).getSaleListProduct().getId());
            }
        }
        return "上传成功";
    }

    @ResponseBody
    @RequestMapping("/static/ceshi")
    public String ceshi(String name, String psw) {
        if (userService.findNamePsw(name, psw) == null) {
            return "用户名或密码错误";
        }
        return "连接成功";
    }

    // 根据数据库中的信息输出二维码
    @ResponseBody
    @RequestMapping("/static/erweima")
    public Map<String, Object> erweima(HttpServletRequest request, String url) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(url);
        String urlInfo = QRCode.getQRCode(request, url);
//		String urlInfo = this.getURLInfo(url, "utf-8");
//		System.out.println("图片路径：");
//		System.out.println(urlInfo);
        map.put("success", true);
        map.put("url", urlInfo);
        return map;
    }

    public String getURLInfo(String urlInfo, String charset) throws Exception {

        URL url = new URL(urlInfo);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        InputStream is = httpUrl.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        is.close();
        br.close();

        Document document = Jsoup.parse(new String(sb));
        Element img = document.select("img").first();
        String href = img.attr("src");

        return href;

    }

    @ResponseBody
    @RequestMapping("/static/selectProduct")
    public List<Product> selectProductByName(String s) {
        if (s == null) {
            s = "";
        }
        return productService.findByName("%" + s + "%");
    }

    @ResponseBody
    @RequestMapping("/static/xiadan")
    public String xiadan() {
        return "下单成功";
    }

    @ResponseBody
    @RequestMapping("/admin/daochu")
    public Map<String, Object> daochu(String src, String a, String title) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println(a);
        DaochuUtil.daochuExcel(a, title);
        map.put("success", true);
        return map;
    }

}