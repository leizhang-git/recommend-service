package com.recommend.consumer.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Describe 测试分布式锁使用
 * @Author zhanglei
 * @Date 2023/7/17 9:41
 */
@Data
@TableName("db_stock")
public class Stock {
    @TableId
    private Long id;
    private String productCode;
    private String stockCode;
    private Integer count;
}
