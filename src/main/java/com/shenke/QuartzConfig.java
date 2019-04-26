package com.shenke;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
public class QuartzConfig {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss");

    @Scheduled(fixedRate = 1000*60*60*24*7)
    public void reportCurrent() {
        StringBuilder cmd = null;
        // 设置文件名，根据时间来写
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(Calendar.getInstance().getTime()) + "db_erp";
        // 实例化cmd对象
        cmd = new StringBuilder();
        // 一下操作是做cmd命令的拼写
        cmd.append("cmd.exe /C mysqldump -u");
        cmd.append("root");
        cmd.append(" -p");
        cmd.append("root");
        cmd.append(" ");
        cmd.append("db_erp");
        cmd.append(" > ");
        cmd.append("D:/数据库文件");
        cmd.append("/" + fileName + ".sql");
        // 获取一个Runtime对象
        Runtime r = Runtime.getRuntime();
        try {
            // 执行cmd备份操作
            r.exec(cmd.toString());
            System.out.println("数据库备份成功");
        } catch (IOException e) {
            System.exit(0);
        }
    }
}