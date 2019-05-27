package com.shenke.util;

import cn.hutool.core.collection.CollUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import java.util.ArrayList;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2019/5/27 16:49
 * @Description:
 */
public class DaochuUtil {
    //导出
    public static void daochuExcel(String str, String title) throws Exception {
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
        JsonArray asJsonArray = jsonParser.parse(str).getAsJsonArray();
        ArrayList<Map<String, String>> mapArrayList = CollUtil.newArrayList();
        int size = 0;
        for (JsonElement user : asJsonArray) {
            //使用GSON，直接转成Bean对象
            Map<String, String> map = gson.fromJson(user, Map.class);
            mapArrayList.add(map);
            size = map.size();
        }


//        FileOutputStream fileOut=new FileOutputStream("d:\\工作簿.xls");
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("d:/123.xls");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(size - 1, title);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(mapArrayList, true);
        // 关闭writer，释放内存
        writer.close();

    }
}
