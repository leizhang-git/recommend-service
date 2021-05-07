package cn.imut.ncee.controller;

import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.MessageBoardService;
import cn.imut.ncee.utils.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private MessageBoardService messageBoardService;

    /**
     * 分页展示留言板信息
     * @param infos id
     * @return 用户留言内容
     */
    @PostMapping("/queryMessage")
    public Results<?> queryAll(@RequestBody Map<String,String> infos) {
        List<MessageBoard> messageBoards = messageBoardService.queryAll(infos.get("uName"));
        return Results.dataOf(messageBoards);
    }

    /**
     * 根据高校Id删除高校
     * @param infos 用户Id
     * @return 是否成功删除
     */
    @DeleteMapping("/deleteById")
    public Results<?> deleteById(@RequestBody Map<String,String> infos) {
        boolean isSuccess = messageBoardService.deleteById(infos.get("uId"));
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
