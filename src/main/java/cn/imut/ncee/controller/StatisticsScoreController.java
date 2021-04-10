package cn.imut.ncee.controller;

import cn.imut.ncee.entity.vo.StatisticsScoreInfo;
import cn.imut.ncee.service.StatisticsScoreService;
import cn.imut.ncee.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        List<String> scores = statisticsScoreService.selectScore(uId, mId);
        return Results.dataOf(scores);
    }
}
