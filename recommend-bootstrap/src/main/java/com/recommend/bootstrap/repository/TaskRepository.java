package com.recommend.bootstrap.repository;

import com.recommend.bootstrap.domain.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/13 16:02
 */
@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String>, JpaSpecificationExecutor<TaskEntity> {

    List<TaskEntity> findAllByTaskName(String taskName);

    List<TaskEntity> findAllByJobId(String jobId);
}