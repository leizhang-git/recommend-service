package cn.imut.ncee.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 留言板
 * @Author zhanglei
 * @Date 2021/4/10 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageBoard {

    /**
     * 用户Id
     */
    private String uId;

    /**
     * 用户名字
     */
    private String uName;

    /**
     * 发表时间（时间戳）
     */
    private String uTime;

    /**
     * 留言内容
     */
    private String uDate;
}
