package cn.imut.ncee.controller;

import cn.imut.ncee.entity.vo.UniversityMajorInfo;
import cn.imut.ncee.service.UniversityMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 高校-专业
 * @Author zhanglei
 * @Date 2021/2/26 10:41
 */
@RestController
@RequestMapping("/universityMajor")
public class UniversityMajorController {

    @Autowired
    private UniversityMajorService universityMajorService;

    /**
     * 管理员添加高校-专业信息
     * @param universityMajorInfo 高校-专业
     * @return 是否添加成功
     */
    @PostMapping("/insert")
    public ResponseEntity<?> insertInfo(UniversityMajorInfo universityMajorInfo) {
        universityMajorService.insertUniversityMajorInfo(universityMajorInfo);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
