package cn.imut.ncee.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 录取分数线
 * @Author zhanglei
 * @Date 2021/4/27 15:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryScore {

    /**
     * 高校Id
     */
    private String universityId;

    /**
     * 专业Id
     */
    private String majorId;

    /**
     * 高校名称
     */
    private String universityName;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 专业类别
     */
    private String majorCategory;

    /**
     * 年份
     */
    private Integer years;

    /**
     * 最低分
     */
    private double minScore;

    /**
     * 平均分
     */
    private double avgScore;

    /**
     * 最高分
     */
    private double maxScore;

    private Integer ranking;

    public EntryScore(String universityId, String majorId, String majorName, String majorCategory, Integer years, double minScore, double avgScore, double maxScore) {
        this.universityId = universityId;
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorCategory = majorCategory;
        this.years = years;
        this.minScore = minScore;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
    }

    public EntryScore(String universityId, String majorId, Integer years, double minScore, double avgScore, double maxScore) {
        this.universityId = universityId;
        this.majorId = majorId;
        this.years = years;
        this.minScore = minScore;
        this.avgScore = avgScore;
        this.maxScore = maxScore;
    }
}
