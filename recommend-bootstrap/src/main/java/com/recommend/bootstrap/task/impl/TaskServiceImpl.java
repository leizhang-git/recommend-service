package com.recommend.bootstrap.task.impl;

import cn.hutool.core.collection.CollUtil;
import com.recommend.bootstrap.domain.entity.TaskEntity;
import com.recommend.bootstrap.repository.TaskRepository;
import com.recommend.bootstrap.task.TaskService;
import com.recommend.provider.util.JSONUtil;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 15:59
 */
@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final static String MESSAGE = "recommend-Task";

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> findAllTask() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskEntity> findTaskByName(String taskName) {
        List<TaskEntity> allTask = taskRepository.findAllByTaskName(taskName);
        List<TaskEntity> result = Lists.newArrayList();
        if(CollUtil.isEmpty(allTask)) {
            return result;
        }else {
            result.add(allTask.get(0));
        }
        return result;
    }

    @Override
    public List<TaskEntity> findTaskById(String taskId) {
        return taskRepository.findAllByJobId(taskId);
    }

    @Override
    public Boolean startTask(String taskId) {
        List<TaskEntity> taskEntityList = taskRepository.findAllByJobId(taskId);
        if(CollUtil.isNotEmpty(taskEntityList)) {
            TaskEntity taskEntity = taskEntityList.get(0);
            taskEntity.setJobStatus(1);
            taskRepository.save(taskEntity);
            redisConvertAndSend(taskEntity);
        }
        return true;
    }

    @Override
    public Boolean stopTask(String taskId) {
        List<TaskEntity> taskEntityList = taskRepository.findAllByJobId(taskId);
        if(CollUtil.isNotEmpty(taskEntityList)) {
            TaskEntity taskEntity = taskEntityList.get(0);
            taskEntity.setJobStatus(0);
            taskRepository.save(taskEntity);
            redisConvertAndSend(taskEntity);
        }
        return true;
    }

    public void redisConvertAndSend(TaskEntity taskEntity) {
        String json = JSONUtil.gsonString(taskEntity);
        redisTemplate.convertAndSend(MESSAGE, json);
    }
}
