package cn.imut.ncee.controller;

import cn.imut.ncee.entity.vo.EntryScore;
import cn.imut.ncee.entity.vo.MajorScore;
import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import cn.imut.ncee.service.StatisticsScoreService;
import cn.imut.ncee.utils.Results;
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

    @Autowired
    private StatisticsScoreService statisticsScoreService;

    /**
     * 管理员添加高校-专业信息
     * @param statisticsScoreInfo 高校-专业
     * @return 是否添加成功
     */
    @PostMapping("/insert")
    public Results<?> insertInfo(StatisticsScoreInfo statisticsScoreInfo) {
        boolean isSuccess = statisticsScoreService.insertStatisticsScore(statisticsScoreInfo);
        return Results.dataOf(isSuccess);
    }

    /**
     * 查询某个高校所有的专业
     * @param uId 高校Id
     * @return 该高校所有专业（专业名称）
     */
    @GetMapping("/queryByUId")
    public Results<?> queryById(String uId) {
        List<String> majors = statisticsScoreService.selectById(uId);
        return Results.dataOf(majors);
    }

    /**
     * 查询所有
     * @return 高校-专业
     */
    @GetMapping("/queryAll")
    public Results<?> queryAll() {
        List<StatisticsScoreInfo> statisticsScoreInfos = statisticsScoreService.selectStatisticsScore();
        return Results.dataOf(statisticsScoreInfos);
    }


    /**
     * 根据高校Id，专业Id查询出近五年分数线
     * @param uId 高校Id
     * @param mId 专业Id
     * @return 分数线
     */
    @GetMapping("/queryScore")
    public Results<?> queryAll(String uId, String mId) {
        List<StatisticsScoreInfo> scores = statisticsScoreService.selectScore(uId, mId);
        return Results.dataOf(scores);
    }

    /**
     * 根据高校Id查询其专业以及其分数线（近五年）
     * @param uId 高校Id
     * @return 高校-专业-分数
     */
    @GetMapping("/score1")
    public Results<?> queryAllScore(@RequestParam("uId") String uId) {
        List<EntryScore> entryScores = statisticsScoreService.selectAllScore(uId);
        return Results.dataOf(entryScores);
    }

    /**
     * 根据高校Id以及专业信息查询其专业以及其分数线（近五年）
     * @param infos id and majorName
     * @return 高校-专业-分数
     */
    @PostMapping("/score")
    public Results<?> queryAllScore(@RequestBody Map<String,String> infos) {
        List<EntryScore> entryScores = statisticsScoreService.selectAllScore(infos.get("uId"), infos.get("majorName"));
        return Results.dataOf(entryScores);
    }


    /**
     * 只查询某一年的分数线
     * @param uId 高校Id
     * @param mId 专业Id
     * @param years 年份
     * @return 分数线
     */
    @GetMapping("/queryOne")
    public Results<?> queryOne(String uId, String mId, String years) {
        StatisticsScoreInfo statisticsScoreInfo = statisticsScoreService.selectOneScore(uId, mId, years);
        return Results.dataOf(statisticsScoreInfo);
    }

    /**
     * 根据高校Id，专业Id删除高校-专业
     * @param infos uId，mId
     * @return 是否成功删除
     */
    @DeleteMapping("/deleteUM")
    public Results<?> queryOne(@RequestBody Map<String,String> infos) {
        boolean isSuccess = statisticsScoreService.deleteByUidAndMid(infos.get("universityId"), infos.get("majorId"));
        return Results.dataOf(isSuccess);
    }

    /**
     * 增加/修改 分数线vo展示
     * @param entryScore vo展示
     * @return
     */
    @PostMapping("/insertAndUpdate")
    public Results<?> insertAndUpdate(@RequestBody EntryScore entryScore) {
        boolean isSuccess = statisticsScoreService.insertAndUpdate(entryScore);
        return Results.dataOf(isSuccess);
    }

    /**
     * 根据专业名称和年份查询信息
     * @param infos 专业名称，年份
     * @return 录取信息
     */
    @PostMapping("/queryByMajor")
    public Results<?> insertAndUpdate(@RequestBody Map<String,String> infos) {
        List<MajorScore> majorScores = statisticsScoreService.selectByMajor(infos.get("majorName"), infos.get("years"));
        return Results.dataOf(majorScores);
    }

    /**
     * 根据专业名称和年份查询信息
     * @param infos 专业名称，年份
     * @return 录取信息
     */
    @PostMapping("/echarts")
    public Results<?> echartsShow(@RequestBody Map<String,String> infos) {
        List<StatisticsScoreInfo> statisticsScoreInfos = statisticsScoreService.selectAll(infos.get("universityId"), infos.get("majorId"));
        return Results.dataOf(statisticsScoreInfos);
    }
}
