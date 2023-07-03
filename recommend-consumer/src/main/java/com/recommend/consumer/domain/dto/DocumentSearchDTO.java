package com.recommend.consumer.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/29 16:11
 */
@Data
public class DocumentSearchDTO {

    private String id;

    private String name;

    private String author;

    private String authorNational;

    private String intro;

    @JsonProperty("dformat")
    private String dformat;

    @JsonProperty("dclass")
    private String dclass;
}
