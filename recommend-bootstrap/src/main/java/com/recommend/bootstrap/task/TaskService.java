package com.recommend.bootstrap.task;

import com.recommend.bootstrap.domain.entity.TaskEntity;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 15:57
 */
public interface TaskService {

    List<TaskEntity> findAllTask();

    List<TaskEntity> findTaskByName(String taskName);

    List<TaskEntity> findTaskById(String taskId);

    Boolean startTask(String taskId);

    Boolean stopTask(String taskId);
}