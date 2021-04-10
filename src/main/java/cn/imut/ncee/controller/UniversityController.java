package cn.imut.ncee.controller;

import cn.imut.ncee.entity.pojo.MajorInfo;
import cn.imut.ncee.entity.pojo.UniversityInfo;
import cn.imut.ncee.service.UniversityService;
import cn.imut.ncee.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Results<?> queryAll(@RequestParam(defaultValue = "0",required = false) int pageNum,
                                      @RequestParam(defaultValue = "6",required = false) int pageSize) {
        List<UniversityInfo> universityInfos = universityService.selectAllUniversity(pageNum, pageSize);
        return Results.dataOf(universityInfos);
    }

    /**
     * 添加高校
     * @param universityInfo 高校信息
     * @return 是否添加成功
     */
    @PostMapping("/addUniversity")
    public Results<?> insertUniversity(@RequestBody UniversityInfo universityInfo) {
        boolean isSuccess = universityService.insertUniversity(universityInfo);
        return Results.dataOf(isSuccess);
    }

    /**
     * 根据高校Id修改高校Code
     * @param uId 高校Id
     * @param uCode 高校Code
     * @return 是否成功修改
     */
    @PatchMapping("/updateCode")
    public Results<?> updateUCode(@RequestParam String uId, @RequestParam String uCode) {
        boolean isSuccess = universityService.updateUniversity(uId, uCode);
        return Results.dataOf(isSuccess);
    }

    /**
     * 根据高校Id查询高校信息
     * @param uId 高校Id
     * @return 高校信息
     */
    @GetMapping("/selectById")
    public Results<?> selectById(@RequestParam String uId) {
        UniversityInfo universityInfo = universityService.selectUniversityById(uId);
        return Results.dataOf(universityInfo);
    }

    /**
     * 根据高校Id删除高校
     * @param uId 高校Id
     * @return 是否成功删除
     */
    @DeleteMapping("/deleteById")
    public Results<?> deleteById(@RequestParam String uId) {
        boolean isSuccess = universityService.deleteUniversityById(uId);
        return Results.dataOf(isSuccess);
    }
}
