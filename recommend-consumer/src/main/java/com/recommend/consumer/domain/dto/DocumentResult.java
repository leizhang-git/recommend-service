package com.recommend.consumer.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/30 9:32
 */
@Data
public class DocumentResult {

    /**
     * 总数量
     */
    private Integer total;

    List<DocumentDataDTO> documentDataList;
}
