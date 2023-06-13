package com.recommend.bootstrap.controller;

import com.recommend.bootstrap.task.TaskService;
import com.recommend.consumer.web.vm.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 15:57
 */
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private static final Logger log = LoggerFactory.getLogger(ToolController.class);

    @Autowired
    private TaskService taskService;

    public ResultVO<?> getAllTask() {
        return ResultVO.getSuccess(taskService.findAllTask());
    }

    @GetMapping("/findByName/{taskName}")
    public ResultVO<?> getTaskByName(@PathVariable("taskName") String taskName) {
        return ResultVO.getSuccess(taskService.findTaskByName(taskName));
    }

    @GetMapping("/findByName/{taskId}")
    public ResultVO<?> getTaskById(@PathVariable("taskId") String taskId) {
        return ResultVO.getSuccess(taskService.findTaskById(taskId));
    }

    @GetMapping("/start/{taskId}")
    public ResultVO<?> startTask(@PathVariable("taskId") String taskId) {
        return ResultVO.getSuccess(taskService.startTask(taskId));
    }

    @GetMapping("/stop/{taskId}")
    public ResultVO<?> stopTask(@PathVariable("taskId") String taskId) {
        return ResultVO.getSuccess(taskService.stopTask(taskId));
    }
}