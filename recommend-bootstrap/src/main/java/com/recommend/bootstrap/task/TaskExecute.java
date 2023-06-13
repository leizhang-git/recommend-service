package com.recommend.bootstrap.task;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.task.Task;
import cn.hutool.extra.spring.SpringUtil;
import com.recommend.consumer.exception.StrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Describe 定时任务执行器
 * @Author zhanglei
 * @Date 2023/6/13 16:41
 */
@Component
public class TaskExecute {

    private static final Logger log = LoggerFactory.getLogger(TaskExecute.class);

    public void startTask(String taskId, String cron, String className) {
        if (ObjectUtil.hasEmpty(taskId, cron, className)) {
            log.error(">>>>>>> taskId is {}, cron is {}, className is {}", taskId, cron, className);
            throw new StrException("传入的对象有空对象！");
        }
        // 预加载类看是否存在此定时任务类
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new StrException("class not found.");
        }

        Task task = () -> {
            try {
                log.info("start timer:" + className);
                TaskRunner timerTaskRunner = (TaskRunner) SpringUtil.getBean(Class.forName(className));
                timerTaskRunner.action();
            } catch (ClassNotFoundException e) {
                log.error(">>> 任务执行异常：{}", e.getMessage());
            }
        };

        // 开始执行任务
        CronUtil.schedule(taskId, cron, task);
        // 设置秒级别的启用
        CronUtil.setMatchSecond(true);
        // 启动定时器执行器
        CronUtil.start();
    }

    public void stopTask(String taskId) {
        CronUtil.remove(taskId);
    }
}