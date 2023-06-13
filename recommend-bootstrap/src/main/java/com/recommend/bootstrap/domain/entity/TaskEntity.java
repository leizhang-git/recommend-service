package com.recommend.bootstrap.domain.entity;

import com.recommend.consumer.domain.DefaultEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 15:21
 */
@Entity
@Table(name = "task_entity")
@Getter
@Setter
public class TaskEntity extends DefaultEntity {

    private static final long serialVersionID = 1L;

    /**
     * 主键uuid
     *
     */
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", unique = true, nullable = false, updatable = false, length = 36)
    private String id;

    /**
     * 任务id
     */
    @Size(max = 50)
    @Column(length = 50)
    private String jobId;

    /**
     * 任务名称
     */
    @Size(max = 50)
    @Column(length = 50)
    private String taskName;

    /**
     * 执行任务的全路径名称，需要实现TaskRunner
     */
    @Size(max = 255)
    @Column(length = 255)
    private String actionClass;

    /**
     * 任务表达式
     */
    @Size(max = 254)
    @Column(length = 254)
    private String taskCron;

    /**
     * 执行状态，0：停止 1：执行
     */
    @Size(max = 20)
    @Column(length = 20)
    private Integer jobStatus = 0;

    /**
     * 备注信息
     */
    @Size(max = 254)
    @Column(length = 254)
    private String remarkInfo;

}