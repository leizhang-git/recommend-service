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

import java.util.List;
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

    @Autowired
    private MajorService majorService;


    /**
     * 插入专业信息(目前不需要高校Id)
     * @param majorInfo 专业信息
     * @return 成功
     */
    @PostMapping("/addMajor")
    public Results<?> insertMajorInfo(@RequestBody MajorInfo majorInfo) {
        boolean isSuccess = majorService.insertMajorInfo(majorInfo);
        return Results.dataOf(isSuccess);
    }

    /**
     * 根据专业Id更新专业信息
     * @param majorInfo 新的专业信息
     * @param majorId 专业Id
     * @return 是否更新成功
     */
    @PostMapping("/updateMajor")
    public Results<?> updateMajorInfo(@RequestBody MajorInfo majorInfo, String majorId) {
        boolean isSuccess = majorService.updateMajorInfo(majorInfo, majorId);
        return Results.dataOf(isSuccess);
    }

    /**
     * 根据专业Code获取专业Id
     * @param majorCode 专业编码
     * @return 专业Id
     */
    @GetMapping("/queryByCode")
    public Results<?> selectIdFromCode(String majorCode) {
        String majorId = majorService.selectIdByCode(majorCode);
        return Results.dataOf(majorId);
    }

    /**
     * 获取全部专业信息
     * @param pageNum 页码
     * @param pageSize 每页的显示数量
     * @return 智能辅助系统中全部专业信息
     */
    @GetMapping("/selectAll")
    public Results<?> selectAllMajorInfo(@RequestParam(defaultValue = "1",required = false) int pageNum,
                                         @RequestParam(defaultValue = "5",required = false) int pageSize) {
        Map<String,Object> majorInfos = majorService.selectAllMajorInfo(pageNum, pageSize);
        return Results.dataOf(majorInfos);
    }

    /**
     * 根据专业Id获取专业信息
     * @param majorId 专业Id
     * @return 专业信息
     */
    @GetMapping("/selectById")
    public Results<?> selectMajorInfoById(String majorId) {
        MajorInfo majorInfo = majorService.selectByIdMajorInfo(majorId);
        return Results.dataOf(majorInfo);
    }

    /**
     * 根据专业Id获取专业科目（文科/理科）
     * @param majorId 专业Id
     * @return 0理科/1文科/-1文/理
     */
    @GetMapping("/selectSubject")
    public Results<?> selectSubject(String majorId) {
        Integer subject = majorService.selectSubject(majorId);
        return Results.dataOf(subject);
    }

    /**
     * 获取所有专业的Id
     * @param pageNum 页码
     * @param pageSize 每页展示的数量
     * @return 专业全部Id
     */
    @GetMapping("/selectAllId")
    public Results<?> selectAllId(@RequestParam(defaultValue = "1",required = false) int pageNum,
                                  @RequestParam(defaultValue = "5",required = false) int pageSize) {
        List<String> ids = majorService.selectAllId(pageNum, pageSize);
        return Results.dataOf(ids);
    }
}
