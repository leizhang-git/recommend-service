package com.recommend.provider.web.rest;

import com.recommend.consumer.web.vm.ResultVO;
import com.recommend.provider.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/21 16:36
 */
@RestController
@RequestMapping("/ex")
public class ExcelController {

    @GetMapping("/downTemplate")
    public ResultVO<?> downTemplate(HttpServletResponse response) {
        ExcelUtil.exportExcel("template", "xlsx", response);
        return ResultVO.getSuccess(true);
    }
}
