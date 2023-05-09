package com.recommend.consumer.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户输入专业后，查询开设该专业的所有高校的录取情况(Vo展示)
 * @Author zhanglei
 * @Date 2021/5/9 17:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajorScore {

    /**
     * 高校名称
     */
    private String universityName;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 年份
     */
    private String years;

    /**
     * 平均分
     */
    private String avgScore;

    /**
     * 最低分
     */
    private String minScore;

    /**
     * 最高分
     */
    private String maxScore;
}
