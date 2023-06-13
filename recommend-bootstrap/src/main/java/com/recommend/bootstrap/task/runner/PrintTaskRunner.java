package com.recommend.bootstrap.task.runner;

import com.recommend.bootstrap.task.TaskRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 定时在控制台打印内容
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 15:51
 */
@Component
public class PrintTaskRunner implements TaskRunner {
    @Override
    public void action() {
        //创建日期时间格式化器对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //对时间进行格式化，输出
        String nowTime = formatter.format(LocalDateTime.now());
        System.out.println(">>>>>>>Hello,当前时间是：" + nowTime);
    }
}