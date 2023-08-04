package com.recommend.consumer.domain.dto;

import lombok.Data;

/**
 * @Describe result
 * @Author zhanglei
 * @Date 2023/7/31 9:49
 */
@Data
public class HBaseResult {

    /**
     * 行
     */
    private String row;

    /**
     * 列族
     */
    private String family;

    /**
     * 列
     */
    private String qualifier;

    /**
     * 值
     */
    private String value;
}
