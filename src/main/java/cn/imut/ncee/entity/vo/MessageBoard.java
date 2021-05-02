package cn.imut.ncee.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * 留言板
 * @Author zhanglei
 * @Date 2021/4/10 17:10
 */
@Getter
@Setter
public class MessageBoard {

    /**
     * 用户Id
     */
    @JsonProperty(value = "uId")
    private String uId;

    /**
     * 用户名字
     */
    @JsonProperty(value = "uName")
    private String uName;

    /**
     * 发表时间（时间戳）
     */
    @JsonProperty(value = "uTime")
    private String uTime;

    /**
     * 留言内容
     */
    @JsonProperty(value = "uDate")
    private String uDate;

    public MessageBoard() {
    }

    public MessageBoard(String uId, String uName, String uTime, String uDate) {
        this.uId = uId;
        this.uName = uName;
        this.uTime = uTime;
        this.uDate = uDate;
    }

    @Override
    public String toString() {
        return "MessageBoard{" +
                "uId='" + uId + '\'' +
                ", uName='" + uName + '\'' +
                ", uTime='" + uTime + '\'' +
                ", uDate='" + uDate + '\'' +
                '}';
    }
}
