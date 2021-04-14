package cn.imut.ncee.entity.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhanglei
 * @Date 2021/4/13 22:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmIndex {

    /*
            userIndex.put("subject","0");
        userIndex.put("address", "北京市");
        userIndex.put("score", "333");
        userIndex.put("majorCategory", "计算机类");
     */

    private String subject;

    private String address;

    private String score;

    private String majorCategory;
}
