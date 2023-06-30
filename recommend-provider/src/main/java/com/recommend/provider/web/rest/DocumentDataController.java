package com.recommend.provider.web.rest;

import com.recommend.consumer.domain.dto.DocumentDataDTO;
import com.recommend.consumer.domain.dto.DocumentResult;
import com.recommend.consumer.domain.dto.DocumentSearchDTO;
import com.recommend.consumer.service.DocumentDataService;
import com.recommend.consumer.web.vm.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
                                  @RequestParam(defaultValue = "20", required = false) int pageSize) {
        List<DocumentDataDTO> allData = documentDataService.getAllData(pageNum, pageSize);
        DocumentResult documentResult = new DocumentResult();
        documentResult.setDocumentDataList(allData);
        documentResult.setTotal(documentDataService.getCount());
        return ResultVO.getSuccess(documentResult);
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

    @GetMapping("/getAllDClass")
    public ResultVO<?> delete() {
        return ResultVO.getSuccess(documentDataService.getAllDClass());
    }

    @PostMapping("/insert")
    public ResultVO<?> insert(@RequestBody DocumentDataDTO documentDataDTO) {
        DocumentDataDTO result = documentDataService.saveDocumentData(documentDataDTO);
        return ResultVO.getSuccess(result);
    }

    @PostMapping("/searchData")
    public ResultVO<?> searchData(@RequestBody DocumentSearchDTO documentSearchDTO) {
        List<DocumentDataDTO> documentDataDTOS = documentDataService.searchAll(documentSearchDTO);
        return ResultVO.getSuccess(documentDataDTOS);
    }

    @PostMapping("/exportExcel")
    public ResultVO<?> exportExcel(@RequestBody List<DocumentDataDTO> documentDataDTOs,
                                   HttpServletRequest request, HttpServletResponse response) {
        boolean result = documentDataService.exportExcel(documentDataDTOs, request, response);
        return ResultVO.getSuccess(result);
    }

    @PostMapping("/upload")
    public ResultVO<?> uploadDocument(@RequestPart("file") MultipartFile file) throws IOException {
        boolean result = documentDataService.uploadDocument(file);
        return ResultVO.getSuccess(result);
    }
}
