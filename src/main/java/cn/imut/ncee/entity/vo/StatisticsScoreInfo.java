package cn.imut.ncee.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 高校专业分数表
 * @Author zhanglei
 * @Date 2021/3/6 14:01
 */
@Getter
@Setter
public class StatisticsScoreInfo {

    /**
     * 高校编号
     */
    private String universityId;

    /**
     * 专业编号
     */
    private String majorId;

    /**
     * 年份
     */
    private Integer years;

    /**
     * 平均分
     */
    private double avgScore;

    /**
     * 最低分
     */
    private double minScore;

    /**
     * 最高分
     */
    private double maxScore;
}
