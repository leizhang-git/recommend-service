package cn.imut.ncee.controller;

import cn.imut.ncee.service.InitService;
import cn.imut.ncee.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @auther zhanglei
 * @create 2023-04-30-23:45
 */
@RestController
@RequestMapping("/init")
public class InitController {

    @Autowired
    private InitService initService;

    private final Logger log = LoggerFactory.getLogger(InitController.class);


    @GetMapping("/getMarcoValues")
    public ResultVO<?> getMarcoLabels(HttpServletRequest request){
        Map<String, Object> marcoLabels = null;
        try {
            marcoLabels = initService.getMarcoValues(request);
        }catch (Exception e){
            log.error("getMarcoLabels error!",e);
        }
        return ResultVO.getSuccess(marcoLabels);
    }
}
