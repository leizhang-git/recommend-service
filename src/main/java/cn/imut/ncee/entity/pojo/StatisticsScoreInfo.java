package cn.imut.ncee.entity.pojo;

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
