package cn.imut.ncee.controller;

import cn.hutool.core.util.StrUtil;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.MessageBoardService;
import cn.imut.ncee.util.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 留言板
 * @Author zhanglei
 * @Date 2021/4/30 10:22
 */
@RestController
@RequestMapping("/api/v1/message")
public class MessageBoardController {

    @Resource
    private MessageBoardService messageBoardService;

    /**
     * 分页展示留言板信息
     * @param infos id
     * @return 用户留言内容
     */
    @PostMapping("/queryMessage")
    public Results<?> queryAll(@RequestBody Map<String,String> infos) {
        if(StrUtil.isNotEmpty(infos.get("beginTime"))  && StrUtil.isNotEmpty(infos.get("outTime"))) {
            if(StrUtil.isNotEmpty(infos.get("uName"))) {
                List<MessageBoard> messageBoards = messageBoardService.queryAll1(infos.get("uName"), Long.parseLong(infos.get("beginTime")), Long.parseLong(infos.get("outTime")));
                return Results.dataOf(messageBoards);
            }else {
                List<MessageBoard> messageBoards = messageBoardService.queryAll2(Long.parseLong(infos.get("beginTime")), Long.parseLong(infos.get("outTime")));
                return Results.dataOf(messageBoards);
            }
        }else {
            List<MessageBoard> messageBoards = messageBoardService.queryAll(infos.get("uName"));
            return Results.dataOf(messageBoards);
        }
    }

    /**
     * 根据用户Id，留言时间删除留言内容
     * @param infos 用户Id
     * @return 是否成功删除
     */
    @DeleteMapping("/deleteById")
    public Results<?> deleteById(@RequestBody Map<String,String> infos) throws ParseException {
        boolean isSuccess = messageBoardService.deleteById(infos.get("uId"), infos.get("uTime"));
        return Results.dataOf(isSuccess);
    }

    /**
     * 增加留言
     * @param messageBoard 留言板
     * @return 是否成功添加留言
     */
    @PostMapping("/addMessage")
    public Results<?> addMessage(@RequestBody MessageBoard messageBoard) {
        boolean isSuccess = messageBoardService.addMessage(messageBoard);
        return Results.dataOf(isSuccess);
    }
}
