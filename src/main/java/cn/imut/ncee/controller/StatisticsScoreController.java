package cn.imut.ncee.controller;

import cn.imut.ncee.entity.vo.EntryScore;
import cn.imut.ncee.entity.vo.MajorScore;
import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import cn.imut.ncee.service.StatisticsScoreService;
import cn.imut.ncee.util.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 高校-专业
 * @Author zhanglei
 * @Date 2021/2/26 10:41
 */
@RestController
@RequestMapping("/api/v1/statis")
public class StatisticsScoreController {

    private static Logger log = LoggerFactory.getLogger(StatisticsScoreController.class);

    @Autowired
    private StatisticsScoreService statisticsScoreService;

    /**
     * 管理员添加高校-专业信息
     * @param statisticsScoreInfo 高校-专业
     * @return 是否添加成功
     */
    @PostMapping("/insert")
    public ResultVO<?> insertInfo(StatisticsScoreInfo statisticsScoreInfo) {
        boolean isSuccess = statisticsScoreService.insertStatisticsScore(statisticsScoreInfo);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 查询某个高校所有的专业
     * @param uId 高校Id
     * @return 该高校所有专业（专业名称）
     */
    @GetMapping("/queryByUId")
    public ResultVO<?> queryById(String uId) {
        List<String> majors = statisticsScoreService.selectById(uId);
        return ResultVO.getSuccess(majors);
    }

    /**
     * 查询所有
     * @return 高校-专业
     */
    @GetMapping("/queryAll")
    public ResultVO<?> queryAll() {
        List<StatisticsScoreInfo> statisticsScoreInfos = statisticsScoreService.selectStatisticsScore();
        return ResultVO.getSuccess(statisticsScoreInfos);
    }


    /**
     * 根据高校Id，专业Id查询出近五年分数线
     * @param uId 高校Id
     * @param mId 专业Id
     * @return 分数线
     */
    @GetMapping("/queryScore")
    public ResultVO<?> queryAll(String uId, String mId) {
        List<StatisticsScoreInfo> scores = statisticsScoreService.selectScore(uId, mId);
        return ResultVO.getSuccess(scores);
    }

    /**
     * 根据高校Id查询其专业以及其分数线（近五年）
     * @param uId 高校Id
     * @return 高校-专业-分数
     */
    @GetMapping("/score1")
    public ResultVO<?> queryAllScore(@RequestParam("uId") String uId) {
        List<EntryScore> entryScores = statisticsScoreService.selectAllScore(uId);
        return ResultVO.getSuccess(entryScores);
    }

    /**
     * 根据高校Id以及专业信息查询其专业以及其分数线（近五年）
     * @param infos id and majorName
     * @return 高校-专业-分数
     */
    @PostMapping("/score")
    public ResultVO<?> queryAllScore(@RequestBody Map<String,String> infos) {
        List<EntryScore> entryScores = statisticsScoreService.selectAllScore(infos.get("uId"), infos.get("majorName"));
        return ResultVO.getSuccess(entryScores);
    }


    /**
     * 只查询某一年的分数线
     * @param uId 高校Id
     * @param mId 专业Id
     * @param years 年份
     * @return 分数线
     */
    @GetMapping("/queryOne")
    public ResultVO<?> queryOne(String uId, String mId, String years) {
        StatisticsScoreInfo statisticsScoreInfo = statisticsScoreService.selectOneScore(uId, mId, years);
        return ResultVO.getSuccess(statisticsScoreInfo);
    }

    /**
     * 根据高校Id，专业Id删除高校-专业
     * @param infos uId，mId
     * @return 是否成功删除
     */
    @DeleteMapping("/deleteUM")
    public ResultVO<?> queryOne(@RequestBody Map<String,String> infos) {
        boolean isSuccess = statisticsScoreService.deleteByUidAndMid(infos.get("universityId"), infos.get("majorId"));
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 增加/修改 分数线vo展示
     * @param entryScore vo展示
     * @return
     */
    @PostMapping("/insertAndUpdate")
    public ResultVO<?> insertAndUpdate(@RequestBody EntryScore entryScore) {
        boolean isSuccess = statisticsScoreService.insertAndUpdate(entryScore);
        return ResultVO.getSuccess(isSuccess);
    }

    /**
     * 根据专业名称和年份查询信息
     * @param infos 专业名称，年份
     * @return 录取信息
     */
    @PostMapping("/queryByMajor")
    public ResultVO<?> insertAndUpdate(@RequestBody Map<String,String> infos) {
        if(infos.size() == 0) {
            return ResultVO.getSuccess("");
        }else {
            List<MajorScore> majorScores = statisticsScoreService.selectByMajor(infos.get("majorName"), infos.get("years"));
            return ResultVO.getSuccess(majorScores);
        }
    }

    /**
     * 根据专业名称和年份查询信息
     * @param infos 专业名称，年份
     * @return 录取信息
     */
    @PostMapping("/echarts")
    public ResultVO<?> echartsShow(@RequestBody Map<String,String> infos) {
        List<StatisticsScoreInfo> statisticsScoreInfos = statisticsScoreService.selectAll(infos.get("universityId"), infos.get("majorId"));
        return ResultVO.getSuccess(statisticsScoreInfos);
    }
}
