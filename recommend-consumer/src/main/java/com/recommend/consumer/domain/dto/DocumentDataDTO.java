package com.recommend.consumer.domain.dto;

import lombok.Data;

import java.time.Instant;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/25 17:09
 */
@Data
public class DocumentDataDTO {

    /**
     * 资源Id
     */
    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源线上地址
     */
    private String remoteAddr;

    /**
     * 资源本地地址
     */
    private String localAddr;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 作者国籍
     */
    private String authorNational;

    /**
     * 简介
     */
    private String intro;

    /**
     * 格式
     */
    private String dFormat;

    /**
     * 类别
     */
    private String dClass;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Instant createDate;

    /**
     * 最后一次的修改者
     */
    private String lastModifiedBy;


    /**
     * 最后修改的时间
     */
    private Instant lastModifiedDate;
}
