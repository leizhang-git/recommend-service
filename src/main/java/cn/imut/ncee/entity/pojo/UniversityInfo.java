package cn.imut.ncee.entity.pojo;

import lombok.*;
import java.util.List;

/**
 * 高校信息
 * @Author zhanglei
 * @Date 2021/1/15 19:30
 */
@Getter
@Setter
public class UniversityInfo {

    /**
     * 高校Id
     */
    private String universityId;

    /**
     * 高校名称
     */
    private String universityName;

    /**
     * 高校编码
     */
    private String universityCode;

    /**
     * 高校简介
     */
    private String universityDesc;

    /**
     * 高校所在地
     */
    private String universityAddress;

    /**
     * 高校在招的所有专业
     */
    private List<MajorInfo> majorInfos;

    public UniversityInfo() {

    }

    public UniversityInfo(String universityName, String universityCode, String universityDesc, String universityAddress) {
        this.universityName = universityName;
        this.universityCode = universityCode;
        this.universityDesc = universityDesc;
        this.universityAddress = universityAddress;
    }

    public UniversityInfo(String universityId, String universityName, String universityCode) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.universityCode = universityCode;
    }

    @Override
    public String toString() {
        return "UniversityInfo{" +
                "universityId='" + universityId + '\'' +
                ", universityName='" + universityName + '\'' +
                ", universityCode='" + universityCode + '\'' +
                ", majorInfos=" + majorInfos +
                '}';
    }
}
