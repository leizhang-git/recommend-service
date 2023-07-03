package com.recommend.consumer.domain.pojo.documentData;

import lombok.Data;

import java.time.Instant;

/**
 * @Describe 资源类
 * @Author zhanglei
 * @Date 2023/6/25 16:58
 */
@Data
public class DocumentData {

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
    private String dformat;

    /**
     * 类别
     */
    private String dclass;

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
