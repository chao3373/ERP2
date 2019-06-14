package com.shenke.controller.admin;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

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

    @RequestMapping("/importFile")
    public Map<String, Object> getExcel(@RequestParam("fileName") MultipartFile file) {// MultipartFile
        // 接收来自表单的File文件，然后进行服务器的上传
        // new HashMap<>();
        System.out.println(file);
        // 文件名
        String fileName = file.getOriginalFilename(); // 获取上传时的文件名

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

        // 设置生产米数小数点后面的位数
        DecimalFormat df = new DecimalFormat("0.0000"); // DecimalFormat 数字格式化
        DecimalFormat dd = new DecimalFormat("0.00"); //格式化保留两位小数

        // 用来存放订单商品的集合
        List<SaleListProduct> list = new ArrayList<SaleListProduct>();
        // 循环工作表的行
        for (Row row : sheet) {
            if (row.getRowNum() != 0 && row.getRowNum() != 1
                    && row.getRowNum() != (sheet.getPhysicalNumberOfRows() - 1)) {
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
                                // map.put("生产米数", df.format(Double.parseDouble(getStringCellValue(cell))));
                                saleListProduct.setMeter(
                                        Double.parseDouble(df.format(Double.parseDouble(getStringCellValue(cell)))));
                            }
                            break;
                        case 6:
                            // map.put("颜色", getStringCellValue(cell));
                            saleListProduct.setColor(getStringCellValue(cell));
                            break;
                        case 7:
                            if (!(StringUtil.isEmpty(getStringCellValue(cell)))) {
                                // map.put("单件重量kg", getStringCellValue(cell));
                                saleListProduct.setOneweight(Double.parseDouble(dd.format(Double.parseDouble(getStringCellValue(cell)))));
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
                                saleListProduct.setSumwight(Double.parseDouble(dd.format(Double.parseDouble(getStringCellValue(cell)))));
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
                            System.out.println("客户名称：" +  clname);
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
				/*System.out.println("执行三");
				System.out.println("我是那个list集合"+list);
			    System.out.println("我是那个map集合"+map);*/
            }
        }
        // map.put("toal", "");
        // map.put("rows", list);
        // System.out.println(map);
        // System.out.println(list);
        //System.out.println("执行四");
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
