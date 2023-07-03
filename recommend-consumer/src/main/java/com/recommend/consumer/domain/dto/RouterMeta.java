package com.recommend.consumer.domain.dto;

import lombok.Data;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/7/3 17:32
 */
@Data
public class RouterMeta {

    private String icon;

    private String title;

    private Boolean noCache;
}
