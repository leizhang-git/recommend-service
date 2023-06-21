package com.recommend.provider.web.rest;

import com.recommend.provider.util.IPAddressUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author zhanglei
 * @Date 2021/3/1 14:58
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello,World";
    }

    @GetMapping("/ip")
    public String ip(HttpServletRequest request) {
        String ipAddr = IPAddressUtil.getIpAddr(request);
        return IPAddressUtil.getCityInfo(ipAddr);
    }
}
