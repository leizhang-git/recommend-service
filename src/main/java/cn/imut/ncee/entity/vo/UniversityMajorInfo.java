package cn.imut.ncee.entity.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 高校-专业信息
 * @Author zhanglei
 * @Date 2021/2/26 10:04
 */
@Getter
@Setter
public class UniversityMajorInfo {

    /**
     * 高校Id
     */
    private String universityId;

    /**
     * 专业Id
     */
    private String majorId;

    public UniversityMajorInfo() {

    }

    public UniversityMajorInfo(String universityId, String majorId) {
        this.universityId = universityId;
        this.majorId = majorId;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    @Override
    public String toString() {
        return "UniversityMajorInfo{" +
                "universityId='" + universityId + '\'' +
                ", majorId='" + majorId + '\'' +
                '}';
    }
}
