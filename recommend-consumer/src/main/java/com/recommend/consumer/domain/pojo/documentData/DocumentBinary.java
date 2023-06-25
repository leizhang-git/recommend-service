package com.recommend.consumer.domain.pojo.documentData;

import lombok.Data;

import java.time.Instant;

/**
 * @Describe 二进制文件
 * @Author zhanglei
 * @Date 2023/6/25 17:01
 */
@Data
public class DocumentBinary {

    /**
     * 资源Id
     */
    private String id;

    /**
     * 具体资源
     */
    private String dBinary;

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
