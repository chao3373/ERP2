package com.shenke.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shenke.entity.Product;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.ProductService;
import com.shenke.util.DaochuUtil;
import com.shenke.util.EntityUtils;
import com.shenke.util.QRCode;
import com.shenke.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
        List<Storage> list = storageService.fandAllBySerialNumber(serialNumber, "提货");
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
        String ck = storageService.genCode();
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println(wholeStr);
        String[] split = wholeStr.split(",");
        storageService.findByState("");
        for (int i = 0; i < split.length; i++) {
            if (StringUtil.isNotEmpty(split[i])) {
                storageService.updateStateById("装车", Integer.parseInt(split[i]), new Date(), ck);
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
        System.out.println(url);
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(url);
        String urlInfo = QRCode.getQRCode(request, url);
//		String urlInfo = this.getURLInfo(url, "utf-8");
//		System.out.println("图片路径：");
//		System.out.println(urlInfo);
        map.put("success", true);
        map.put("url", urlInfo);
        System.out.println(map);
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
    public Map<String, Object> daochu(String a, String title) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println(a);
        System.out.println(title);
        DaochuUtil.daochuExcel(a, title);
        map.put("success", true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/static/setAccomplishNumber")
    public Map<String, Object> getAccomplishNumber(HttpServletRequest request, HttpServletResponse response, Integer sunNum, String accomplishNumber) {
        Integer acco;
        if (accomplishNumber.equals("null") || accomplishNumber == null || accomplishNumber == "") {
            acco = 0;
        } else {
            acco = Integer.parseInt(accomplishNumber);
        }
        Map<String, Object> map = new HashMap<>();
        ServletContext servletContext = request.getSession().getServletContext();
        servletContext.setAttribute("sunNum", sunNum);
        servletContext.setAttribute("accomplishNumber", acco);
        map.put("success", true);
        return map;
    }

    @ResponseBody
    @RequestMapping("/static/accomplishNumberCount")
    public Map<String, Object> accomplishNumberCount(HttpServletResponse response, HttpServletRequest request, Integer id, String jiTai) {
        Map<String, Object> map = new HashMap<>();
        ServletContext servletContext = request.getSession().getServletContext();
        Integer accomplishNumber = (Integer) servletContext.getAttribute("accomplishNumber");
        accomplishNumber++;
        System.out.println(accomplishNumber);
        if (Integer.parseInt(servletContext.getAttribute("sunNum").toString()) == accomplishNumber) {
            saleListProductRepository.updateState("生产完成：" + jiTai, id);
            saleListProductRepository.updateIussueState("生产完成：" + jiTai, id);
            map.put("success", false);
        } else {
            servletContext.removeAttribute("accomplishNumber");
            servletContext.setAttribute("accomplishNumber", accomplishNumber);
            map.put("success", true);
            map.put("accomplishNumber", accomplishNumber);
            return map;
        }
        return null;
    }

}