package com.recommend.consumer.exception;

import com.recommend.consumer.util.*;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 21:37
 */
public class MiddlewareException extends BaseException{

    public MiddlewareException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, SystemTimeUtil.getCurrentTime(), SystemMDCUtil.getTraceId(), cause);
    }
}
