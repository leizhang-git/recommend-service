package com.recommend.bootstrap.task.listen;

import com.recommend.bootstrap.domain.entity.TaskEntity;
import com.recommend.bootstrap.task.TaskExecute;
import com.recommend.provider.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 16:52
 */
@Component
public class RedisListen extends MessageListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(RedisListen.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private TaskExecute taskExecute;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        byte[] body = message.getBody();
        String msg = (String)redisTemplate.getStringSerializer().deserialize(body);
        TaskEntity taskEntity = JSONUtil.gsonToBean(msg, TaskEntity.class);
        if (taskEntity.getJobStatus() == 1) {
            taskExecute.startTask(String.valueOf(taskEntity.getJobId()), taskEntity.getTaskCron(), taskEntity.getActionClass());
        } else if (taskEntity.getJobStatus() == 0) {
            taskExecute.stopTask(String.valueOf(taskEntity.getJobId()));
        } else {
            log.info("没有任务启动");
        }
    }
}