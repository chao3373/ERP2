package com.shenke.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.shenke.entity.Product;
import com.shenke.service.*;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shenke.entity.Log;
import com.shenke.entity.SaleListProduct;
import com.shenke.util.StringUtil;

/**
 * 订单导入Controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/admin/toLead")
public class ToLeadController {

    @Resource
    private ToLeadService toLeadService;

    @Resource
    private LogService logService;

    @Resource
    private ProductService productService;

    @Resource
    private WightService wightService;

    @Resource
    private DaoService daoService;

    @Resource
    private BrandService brandService;

    @Resource
    private PackService packService;

    @Resource
    private LetterService letterService;

    @Resource
    private ClientService clientService;

    @RequestMapping("/import")
    public Map<String, Object> importt(@RequestParam("fileName") MultipartFile file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        String errorInfo = "";
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<Map<String, Object>> mapList = reader.readAll();
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < mapList.size(); i++) {
            int j = i + 1;
            Map<String, Object> map1 = mapList.get(i);
            map1.put("name", map1.remove("产品名称"));
            map1.put("model", map1.remove("宽度m"));
            map1.put("price", map1.remove("厚度mm"));
            map1.put("length", map1.remove("长度m"));
            map1.put("meter", map1.remove("实际厚度mm"));
            map1.put("color", map1.remove("颜色"));
            map1.put("oneweight", map1.remove("重量"));
            map1.put("num", map1.remove("件数"));
            map1.put("sumwight", map1.remove("总重量"));
            map1.put("realitymodel", map1.remove("实际幅宽m"));
            map1.put("demand", map1.remove("要求"));
            map1.put("weightset", map1.remove("重量设置"));
            map1.put("dao", map1.remove("剖刀设置"));
            map1.put("brand", map1.remove("商标设置"));
            map1.put("pack", map1.remove("包装设置"));
            map1.put("letter", map1.remove("印字设置"));
            map1.put("peasant", map1.remove("客户姓名"));
            map1.put("clientname", map1.remove("客户名称"));
            map1.put("dingJia", map1.remove("订价"));

            System.out.println(map1);

            System.out.println("name: " + map1.get("name"));
            System.out.println("weightset: " + map1.get("weightset"));
            System.out.println("dao: " + map1.get("dao"));
            System.out.println("brand: " + map1.get("brand"));
            System.out.println("pack: " + map1.get("pack"));
            System.out.println("letter: " + map1.get("letter"));
            System.out.println("clientname: " + map1.get("clientname"));
            if (map1.get("name") == null || map1.get("weightset") == null || map1.get("dao") == null || map1.get("brand") == null || map1.get("pack") == null || map1.get("letter") == null || map1.get("clientname") == null) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行存在空白单元格");
                return map;
            }

            System.out.println(productService.findByName(map1.get("name").toString()));
            System.out.println(productService.findByName(map1.get("name").toString()).size());
            if (map1.get("name") == null || productService.findByName(map1.get("name").toString()).size() == 0) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行产品名称有误");
                return map;
            }

            if (map1.get("weightset") == null || wightService.findByName(map1.get("weightset").toString()) == null) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行重量设置有误");
                return map;
            }

            if (map1.get("dao") == null || daoService.findByName(map1.get("dao").toString()).size() == 0) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行剖刀设置有误");
                return map;
            }
            if (map1.get("brand") == null || brandService.findByName(map1.get("brand").toString()).size() == 0) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行商标设置有误");
                return map;
            }

            if (map1.get("pack") == null || packService.findByName(map1.get("pack").toString()).size() == 0) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行包装设置有误");
                return map;
            }

            if (map1.get("letter") == null || letterService.findByName(map1.get("letter").toString()).size() == 0) {
                map.put("success", false);
                System.out.println(letterService.findByName(map1.get("letter").toString()));
                map.put("errorInfo", "第" + j + "行印字设置有误");
                return map;
            }

            if (map1.get("clientname") == null || clientService.findByName(map1.get("clientname").toString()).size() == 0) {
                map.put("success", false);
                map.put("errorInfo", "第" + j + "行客户姓名有误");
                return map;
            }

            data.add(map1);
        }
        System.out.println(data);
        map.put("success", true);
        map.put("rows", data);
        return map;
    }

    @RequestMapping("/importFile")
    public Map<String, Object> getExcel(@RequestParam("fileName") MultipartFile file) {// MultipartFile
        System.out.println("文件导入");
        // 接收来自表单的File文件，然后进行服务器的上传
        // new HashMap<>();
//        System.out.println(file);
        // 文件名
        String fileName = file.getOriginalFilename(); // 获取上传时的文件名
        System.out.println(fileName);

        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);

        // 工作区域
        Workbook wb = null;

        // 判断文件后缀名
        if (prefix.equals("xlsx")) {
            try {
                wb = new XSSFWorkbook(file.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (prefix.equals("xls")) {
            try {
                wb = new HSSFWorkbook(file.getInputStream());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Sheet sheet = wb.getSheetAt(0);

        // 用来存放内容的map
        Map<String, Object> map = new HashMap<String, Object>();

        // 设置实际厚度小数点后面的位数
        DecimalFormat dd = new DecimalFormat("0.0000"); //格式化保留两位小数

        // 用来存放订单商品的集合
        List<SaleListProduct> list = new ArrayList<SaleListProduct>();
        // 循环工作表的行
        for (Row row : sheet) {
            System.out.println("行号：" + row.getRowNum());
            System.out.println("行数：" + sheet.getPhysicalNumberOfRows());
            if (row.getRowNum() != 0 && row.getRowNum() != 1) {
                // 创建订单中每个商品的对象
                SaleListProduct saleListProduct = new SaleListProduct();
                // 循环每行中的单元格
                for (Cell cell : row) {
                    if (cell.getColumnIndex() == 0 || cell.getRowIndex() == 0 || cell.getRowIndex() == 1) {// getColumnIndexOrThrow
                        continue;
                    }
                    switch (cell.getColumnIndex()) {
                        case 1:
                            // map.put("产品名称", getStringCellValue(cell));
                            String name = getStringCellValue(cell);
                            saleListProduct.setName(getStringCellValue(cell));

                            if (productService.findName(name) != null) {

                            } else {
                                map.put("errorInfo", "无该产品！");
                                map.put("success", false);
                                return map;
                            }
                            break;
                        case 2:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("幅宽m", getStringCellValue(cell));
                                saleListProduct.setModel(Double.parseDouble(getStringCellValue(cell)));

                            }
                            break;
                        case 3:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("厚度mm", getStringCellValue(cell));
                                saleListProduct.setPrice(Double.parseDouble(dd.format(Double.parseDouble(getStringCellValue(cell)))));
                            }
                            break;
                        case 4:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("长度m", getStringCellValue(cell));
                                saleListProduct.setLength(Double.parseDouble(getStringCellValue(cell)));
                            }
                            break;
                        case 5:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("实际厚度", df.format(Double.parseDouble(getStringCellValue(cell))));
                                saleListProduct.setMeter(
                                        Double.parseDouble(dd.format(Double.parseDouble(getStringCellValue(cell)))));
                            }
                            break;
                        case 6:
                            // map.put("颜色", getStringCellValue(cell));
                            saleListProduct.setColor(getStringCellValue(cell));
                            break;
                        case 7:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("单件重量kg", getStringCellValue(cell));
                                saleListProduct.setOneweight((int) Math.round(Double.parseDouble(getStringCellValue(cell))));
                            }
                            break;
                        case 8:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("数量", getStringCellValue(cell));
                                saleListProduct.setNum((int) Double.parseDouble(getStringCellValue(cell)));
                            }
                            break;
                        case 9:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("总重量", getStringCellValue(cell));
                                saleListProduct.setSumwight((int) Math.round(Double.parseDouble(getStringCellValue(cell))));
                            }
                            break;
                        case 10:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("实际幅宽m", getStringCellValue(cell));
                                saleListProduct.setRealitymodel(Double.parseDouble(getStringCellValue(cell)));
                            }
                            break;
                        case 11:
                            // map.put("要求", getStringCellValue(cell));
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                saleListProduct.setDemand(getStringCellValue(cell));
                            }
                            break;
                        case 12:
                            // map.put("重量设置", getStringCellValue(cell));
                            String weightname = getStringCellValue(cell);
                            saleListProduct.setWightset(getStringCellValue(cell));
                            if (wightService.findName(weightname) != null) {

                            } else {
                                map.put("errorInfo", "重量参数有误");
                                map.put("success", false);
                                return map;
                            }
                            break;

                        case 13:
                            // map.put("剖刀设置", getStringCellValue(cell));
                            String daoname = getStringCellValue(cell);
                            saleListProduct.setDao(getStringCellValue(cell));
                            if (daoService.findName(daoname) != null) {

                            } else {
                                map.put("errorInfo", "剖刀设置有误");
                                map.put("success", false);
                                return map;
                            }
                            break;

                        case 14:
                            // map.put("商标设置", getStringCellValue(cell));
                            String biaoname = getStringCellValue(cell);
                            saleListProduct.setBrand(getStringCellValue(cell));
                            if (brandService.findName(biaoname) != null) {
                            } else {
                                map.put("errorInfo", "商标设置有误");
                                map.put("scuuess", false);
                                return map;
                            }
                            break;
                        case 15:
                            // map.put("包装设置", getStringCellValue(cell));
                            String packname = getStringCellValue(cell);
                            saleListProduct.setPack(getStringCellValue(cell));
                            if (packService.findName(packname) != null) {

                            } else {
                                map.put("errorInfo", "包装设置有误");
                                map.put("success", false);
                                return map;
                            }

                            break;

                        case 16:
                            // map.put("印字设置", getStringCellValue(cell));
                            String lettername = getStringCellValue(cell);
                            saleListProduct.setLetter(getStringCellValue(cell));
                            if (letterService.findName(lettername) != null) {

                            } else {
                                map.put("errorInfo", "印字设置有误");
                                map.put("success", false);
                                return map;
                            }

                            break;

                        case 17:
                            // map.put("客户姓名", getStringCellValue(cell));
                            saleListProduct.setPeasant(getStringCellValue(cell));
                            break;

                        case 18:
                            // map.put("客户名称", getStringCellValue(cell));
                            String clname = getStringCellValue(cell);
                            System.out.println("客户名称：" + clname);
                            saleListProduct.setClientname(getStringCellValue(cell));
                            if (clientService.findName(clname) != null) {

                            } else {
                                map.put("errorInfo", "客户姓名有误");
                                map.put("success", false);
                                return map;
                            }
                            break;
                        case 19:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("实际重量", getStringCellValue(cell));
                                saleListProduct.setRealityweight(Double.parseDouble(getStringCellValue(cell)));
                            }
                            break;
                    }
                    String data = getStringCellValue(cell);
                }
                map.put("success", true); // 成功
                map.put("rows", list);
                list.add(saleListProduct);
            }
        }
        logService.save(new Log(Log.ADD_ACTION, "销售订单导入"));
        return map;
    }

    // 读取单元格内容 并转为字符串
    private static String getStringCellValue(Cell cell) {

        String strCell;

        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("")) {
            return "";
        }
        return strCell;
    }
}
