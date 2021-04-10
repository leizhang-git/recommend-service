package cn.imut.ncee.controller;

import cn.imut.ncee.entity.pojo.UniversityInfo;
import cn.imut.ncee.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 高校
 * @Author zhanglei
 * @Date 2021/1/29 18:46
 */
@RestController
@RequestMapping("/api/v1/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    /**
     * 查询所有高校消息（分页）
     * @param pageNum 页码
     * @param pageSize 每页显示数量
     * @return 成功
     */
    @GetMapping("/queryUniversity")
    public ResponseEntity<?> queryAll(@RequestParam(defaultValue = "0",required = false) int pageNum,
                                      @RequestParam(defaultValue = "6",required = false) int pageSize) {
        List<UniversityInfo> universityInfos = universityService.selectAllUniversity(pageNum, pageSize);
        return ResponseEntity.ok(universityInfos);
    }


}
