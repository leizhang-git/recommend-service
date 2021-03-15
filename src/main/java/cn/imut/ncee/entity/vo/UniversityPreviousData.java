package cn.imut.ncee.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 高校往年录取数据
 *
 * @Author zhanglei
 * @Date 2021/1/15 19:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniversityPreviousData {

    //年份
    private String year;

    //高校代码
    private String universityCode;

    //投档分
    private String enrollScore;

    //投档分位次
    private String enrollScoreRank;

    //录取人次
    private String enrollPerson;
}
