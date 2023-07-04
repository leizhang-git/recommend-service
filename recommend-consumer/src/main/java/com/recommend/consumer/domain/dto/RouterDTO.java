package com.recommend.consumer.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/7/3 17:34
 */
@Data
public class RouterDTO {

    private String name;

    private String path;

    private String redirect;

    private Boolean hidden;

    private String component;

    private Boolean alwaysShow;

    private Meta meta;

    private List<RouterDTO> children;
}
