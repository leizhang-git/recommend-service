package cn.imut.ncee.exception;

import cn.imut.ncee.util.SystemMDCUtil;
import cn.imut.ncee.util.SystemTimeUtil;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 21:37
 */
public class MiddlewareException extends BaseException{

    public MiddlewareException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, SystemTimeUtil.getCurrentTime(), SystemMDCUtil.getTraceId(), cause);
    }
}
