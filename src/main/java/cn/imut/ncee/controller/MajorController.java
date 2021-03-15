package cn.imut.ncee.controller;

import cn.imut.ncee.entity.pojo.MajorInfo;
import cn.imut.ncee.service.MajorService;
import cn.imut.ncee.utils.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 专业
 *
 * @Author zhanglei
 * @Date 2021/1/29 18:46
 */
@RestController
@RequestMapping("/api/v1/major")
public class MajorController {

    private Logger logger = LoggerFactory.getLogger(MajorController.class);

    @Autowired
    private MajorService majorService;

    /**
     * 根据高校Id，分页获取专业信息
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @param universityId 高校Id
     * @return 返回分页后的所有专业信息
     */
    @GetMapping("/queryAll")
    public Results<Map<String,Object>> queryAllMajorInfo(@RequestParam(defaultValue = "1",required = false) int pageNum,
                                          @RequestParam(defaultValue = "2",required = false) int pageSize,
                                          @RequestParam(defaultValue = "universityId", required = false) String universityId) {
        return Results.dataOf(majorService.selectAllMajorInfo(universityId, pageNum, pageSize));
    }

    /**
     * 插入专业信息(目前不需要高校Id)
     * @param majorInfo 专业信息
     * @return 成功
     */
    @PostMapping("/addMajor")
    public ResponseEntity<?> insertMajorInfo(@RequestBody MajorInfo majorInfo) {
        majorService.insertMajorInfo(majorInfo);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }


}
