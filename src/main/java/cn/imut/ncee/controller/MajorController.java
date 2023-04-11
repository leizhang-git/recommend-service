package cn.imut.ncee.controller;

import cn.imut.ncee.domain.entity.pojo.MajorInfo;
import cn.imut.ncee.service.MajorService;
import cn.imut.ncee.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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

    private static Logger log = LoggerFactory.getLogger(MajorController.class);

    @Resource
    private MajorService majorService;


    /**
     * 插入专业信息(目前不需要高校Id)
     * @param majorInfo 专业信息
     * @return 成功
     */
    @PostMapping("/addMajor")
    public ResultVO<?> insertMajorInfo(@RequestBody MajorInfo majorInfo) {
        boolean isSuccess = majorService.insertMajorInfo(majorInfo);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 根据专业Id更新专业信息
     * @param majorInfo 新的专业信息
     * @param majorId 专业Id
     * @return 是否更新成功
     */
    @PatchMapping("/updateMajor")
    public ResultVO<?> updateMajorInfo(@RequestBody MajorInfo majorInfo, String majorId) {
        boolean isSuccess = majorService.updateMajorInfo(majorInfo, majorId);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 根据专业Code获取专业Id
     * @param majorCode 专业编码
     * @return 专业Id
     */
    @GetMapping("/queryByCode")
    public ResultVO<?> selectIdFromCode(String majorCode) {
        String majorId = majorService.selectIdByCode(majorCode);
        return ResultVO.getSuccess(majorId);
    }

    /**
     * 获取全部专业信息
     * @param pageNum 页码
     * @param pageSize 每页的显示数量
     * @return 智能辅助系统中全部专业信息
     */
    @GetMapping("/selectAll")
    public ResultVO<?> selectAllMajorInfo(@RequestParam(defaultValue = "0",required = false) int pageNum,
                                          @RequestParam(defaultValue = "5",required = false) int pageSize) {
        Map<String,Object> majorInfos = majorService.selectAllMajorInfo(pageNum, pageSize);
        return ResultVO.getSuccess(majorInfos);
    }

    /**
     * 根据专业Id获取专业信息
     * @param majorId 专业Id
     * @return 专业信息
     */
    @GetMapping("/selectById")
    public ResultVO<?> selectMajorInfoById(String majorId) {
        MajorInfo majorInfo = majorService.selectByIdMajorInfo(majorId);
        return ResultVO.getSuccess(majorInfo);
    }

    /**
     * 根据专业Id获取专业科目（文科/理科）
     * @param majorId 专业Id
     * @return 0理科/1文科/-1文/理
     */
    @GetMapping("/selectSubject")
    public ResultVO<?> selectSubject(String majorId) {
        Integer subject = majorService.selectSubject(majorId);
        return ResultVO.getSuccess(subject);
    }

    /**
     * 获取所有专业的Id
     * @param pageNum 页码
     * @param pageSize 每页展示的数量
     * @return 专业全部Id
     */
    @GetMapping("/selectAllId")
    public ResultVO<?> selectAllId(@RequestParam(defaultValue = "0",required = false) int pageNum,
                                   @RequestParam(defaultValue = "5",required = false) int pageSize) {
        List<String> ids = majorService.selectAllId(pageNum, pageSize);
        return ResultVO.getSuccess(ids);
    }

    /**
     * 根据专业Id删除该专业
     * @param majorId 专业Id
     * @return 是否成功删除
     */
    @GetMapping("/deleteById")
    public ResultVO<?> deleteById(String majorId) {
        boolean isSuccess = majorService.deleteById(majorId);
        return ResultVO.getSuccess(isSuccess);
    }


}
