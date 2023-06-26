package com.recommend.provider.web.rest;

import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.service.DocumentDataService;
import com.recommend.consumer.web.vm.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/26 9:26
 */
@RestController
@RequestMapping("/document")
public class DocumentDataController {

    @Autowired
    private DocumentDataService documentDataService;

    @GetMapping("/getAllData")
    public ResultVO<?> getAllData(@RequestParam(defaultValue = "1", required = false) int pageNum,
                                  @RequestParam(defaultValue = "10", required = false) int pageSize) {
        List<DocumentDataDTO> allData = documentDataService.getAllData(pageNum, pageSize);
        return ResultVO.getSuccess(allData);
    }

    @GetMapping("/searchAll/{input}")
    public ResultVO<?> searchAll(@PathVariable("input") String input) {
        List<DocumentDataDTO> allData = documentDataService.searchAll(input);
        return ResultVO.getSuccess(allData);
    }

    @GetMapping("/delete/{id}")
    public ResultVO<?> delete(@PathVariable("id") String id) {
        return ResultVO.getSuccess(documentDataService.deleteById(id));
    }

    @GetMapping("/insert")
    public ResultVO<?> insert(@RequestBody DocumentDataDTO documentDataDTO) {
        DocumentDataDTO result = documentDataService.saveDocumentData(documentDataDTO);
        return ResultVO.getSuccess(result);
    }
}
