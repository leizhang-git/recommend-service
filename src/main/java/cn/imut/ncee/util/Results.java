package cn.imut.ncee.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

/**
 * 接口返回结果
 * @Author zhanglei
 * @Date 2021/3/4 19:15
 */
@Data
@NoArgsConstructor
public class Results<T> {

    public static final Results OK = new Results(true);

    public static final Results FAIL = errorOf("操作失败");
    private static final String TOTAL = "total";
    private static final String PAGE_SIZE = "pageSize";
    private static final String PAGE_NUMBER = "pageNumber";

    /**
     * 是否操作成功
     */
    private Boolean success = true;

    /**
     * 异常编码
     */
    private String errorCode;

    /**
     * 错误原因
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    /**
     * 分页信息
     */
    private HashMap page;

    private Results(boolean success) {
        this.success = success;
    }

    private Results(boolean success, String errorCode, String msg) {
        this(success);
        this.errorCode = errorCode;
        this.msg = msg;
    }

    private Results(T data) {
        this.success = true;
        this.data = data;
    }

    private Results(T data, int pageNum, int pageSize, long total) {
        this(data);
        this.page = new HashMap();
        this.page.put(PAGE_NUMBER, pageNum);
        this.page.put(PAGE_SIZE, pageSize);
        this.page.put(TOTAL, total);
    }

    /**
     * 返回结果对象数据
     * @param t 结果对象数据
     * @return
     */
    public static Results dataOf(Object t) {
        return new Results(t);
    }

    /**
     * 使用spring分页对象封装结果返回
     * @param page 分页对象
     * @return
     */
    public static Results pageOf(Page<?> page) {
        return pageOf(page.getContent(), page.getNumber(), page.getSize(), page.getTotalElements());
    }

    /**
     * 将结果封装为分页参数模式返回
     * @param data 集合对象数据
     * @param pageNum 页数
     * @param pageSize 总页数
     * @param total 总条数
     * @return
     */
    public static Results pageOf(List<?> data, int pageNum, int pageSize, long total) {
        return new Results(data, pageNum, pageSize, total);
    }

    /**
     * 默认错误返回，错误码默认为500
     * @param msg 错误消息
     * @return
     */
    public static Results errorOf(String msg) {
        return errorOf("500", msg);
    }

    /**
     * 错误返回，使用自定义错误码
     * @param errorCode 错误码
     * @param msg 错误消息
     * @return
     */
    public static Results errorOf(String errorCode, String msg) {
        return new Results(false, errorCode, msg);
    }
}
