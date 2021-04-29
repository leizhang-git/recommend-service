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
    private String years;

    /**
     * 最低分
     */
    private String minScore;

    /**
     * 平均分
     */
    private String avgScore;

    /**
     * 最高分
     */
    private String maxScore;
}
