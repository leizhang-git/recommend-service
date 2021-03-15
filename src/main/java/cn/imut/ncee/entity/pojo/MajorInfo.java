package cn.imut.ncee.entity.pojo;

import lombok.*;

/**
 * 专业信息
 * @Author zhanglei
 * @Date 2021/1/15 19:35
 */
@Getter
@Setter
public class MajorInfo {

    /**
     * 专业Id
     * 01开始递增
     */
    private String majorId;

    /**
     * 专业名称
     */
    private String majorName;

    /**
     * 专业编号
     * 实际操作中使用编号进行操作
     */
    private String majorCode;

    /**
     * 专业类别
     * 哲学、经济学、法学、教育学、文学、历史学、理学、工学、农学、医学、管理学、艺术学
     */
    private String majorCategory;

    /**
     * 招生人数
     */
    private String enrollPerson;

    /**
     * 高校Id
     */
    private String universityId;

    /**
     * 科目：理科0，文科1，文科/理科-1
     */
    private Integer subject;


    public MajorInfo(String majorName, String majorCode, String majorCategory, String enrollPerson) {
        this.majorName = majorName;
        this.majorCode = majorCode;
        this.majorCategory = majorCategory;
        this.enrollPerson = enrollPerson;
    }

    public MajorInfo(String majorId, String majorName, String majorCode, String majorCategory, String enrollPerson, String universityId) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorCode = majorCode;
        this.majorCategory = majorCategory;
        this.enrollPerson = enrollPerson;
        this.universityId = universityId;
    }

    public MajorInfo(String majorId, String majorName, String majorCode, String majorCategory, String enrollPerson) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.majorCode = majorCode;
        this.majorCategory = majorCategory;
        this.enrollPerson = enrollPerson;
    }

    @Override
    public String toString() {
        return "MajorInfo{" +
                "majorId='" + majorId + '\'' +
                ", majorName='" + majorName + '\'' +
                ", majorCode='" + majorCode + '\'' +
                ", majorCategory='" + majorCategory + '\'' +
                ", enrollPerson='" + enrollPerson + '\'' +
                ", universityId='" + universityId + '\'' +
                '}';
    }
}
