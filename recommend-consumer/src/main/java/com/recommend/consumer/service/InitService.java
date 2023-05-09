package com.recommend.consumer.service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther zhanglei
 * @create 2023-04-30-23:46
 */
public interface InitService {

    Map<String,Object> getMarcoValues(HttpServletRequest request);
}
