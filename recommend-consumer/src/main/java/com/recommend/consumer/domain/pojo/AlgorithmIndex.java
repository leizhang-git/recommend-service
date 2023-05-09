package com.recommend.consumer.domain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhanglei
 * @Date 2021/4/13 22:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmIndex {

    /**
     * 科目（0：理科，1：文科）
     */
    private String subject;

    /**
     * 高校地址
     */
    private String address;

    /**
     * 分数
     */
    private String score;

    /**
     * 专业类别（交通运输类、力学类、化学类、哲学类....）
     */
    private String majorCategory;
}
