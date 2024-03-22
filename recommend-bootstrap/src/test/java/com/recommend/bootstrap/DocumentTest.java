package com.recommend.bootstrap;

import com.recommend.consumer.domain.dto.DocumentDataDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/6/26 10:18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DocumentTest {

    @Autowired
    private DocumentDataService documentDataService;

    @Test
    public void testInsert() {

        DocumentDataDTO documentDataDTO = new DocumentDataDTO();
        documentDataDTO.setName("arthas简单介绍和使用");
        documentDataDTO.setRemoteAddr(null);
        documentDataDTO.setLocalAddr("D:\\work\\document\\");
        documentDataDTO.setAuthor("zhanglei");
        documentDataDTO.setAuthorNational("中国");
        documentDataDTO.setIntro("简单介绍一些arthas使用以及cglib&asm");
        documentDataDTO.setDformat("docx");
        documentDataDTO.setCreateBy("admin");
        documentDataDTO.setDclass(DocumentClassEnum.JAVA.getValue());
        documentDataService.saveDocumentData(documentDataDTO);

        //=================================================================
        DocumentDataDTO documentDataDTO1 = new DocumentDataDTO();
        documentDataDTO1.setName("k8s命令");
        documentDataDTO1.setRemoteAddr(null);
        documentDataDTO1.setLocalAddr("D:\\work\\document\\");
        documentDataDTO1.setAuthor("zhanglei");
        documentDataDTO1.setAuthorNational("中国");
        documentDataDTO1.setIntro("简单介绍k8s以及命令");
        documentDataDTO1.setDformat("txt");
        documentDataDTO1.setCreateBy("admin");
        documentDataDTO1.setDclass(DocumentClassEnum.CLOUD.getValue());
        documentDataService.saveDocumentData(documentDataDTO1);

        //=================================================================
        DocumentDataDTO documentDataDTO2 = new DocumentDataDTO();
        documentDataDTO2.setName("Nginx配置文件");
        documentDataDTO2.setRemoteAddr(null);
        documentDataDTO2.setLocalAddr("D:\\work\\document\\");
        documentDataDTO2.setAuthor("zhanglei");
        documentDataDTO2.setAuthorNational("中国");
        documentDataDTO2.setIntro("nginx配置文件");
        documentDataDTO2.setDformat("conf");
        documentDataDTO2.setCreateBy("admin");
        documentDataDTO2.setDclass(DocumentClassEnum.CONF.getValue());
        documentDataService.saveDocumentData(documentDataDTO2);

        //=================================================================
        DocumentDataDTO documentDataDTO3 = new DocumentDataDTO();
        documentDataDTO3.setName("序列化框架整理");
        documentDataDTO3.setRemoteAddr(null);
        documentDataDTO3.setLocalAddr("D:\\work\\document\\");
        documentDataDTO3.setAuthor("zhanglei");
        documentDataDTO3.setAuthorNational("中国");
        documentDataDTO3.setIntro("简单介绍序列化框架");
        documentDataDTO3.setDformat("docx");
        documentDataDTO3.setCreateBy("admin");
        documentDataDTO3.setDclass(DocumentClassEnum.TOOL.getValue());
        documentDataService.saveDocumentData(documentDataDTO3);

        //=================================================================
        DocumentDataDTO documentDataDTO4 = new DocumentDataDTO();
        documentDataDTO4.setName("简单记录，后续可以分析一下");
        documentDataDTO4.setRemoteAddr(null);
        documentDataDTO4.setLocalAddr("D:\\work\\document\\");
        documentDataDTO4.setAuthor("zhanglei");
        documentDataDTO4.setAuthorNational("中国");
        documentDataDTO4.setIntro("记录自己入职以来每天都做的事情");
        documentDataDTO4.setDformat("txt");
        documentDataDTO4.setCreateBy("admin");
        documentDataDTO4.setDclass(DocumentClassEnum.WORK.getValue());
        documentDataService.saveDocumentData(documentDataDTO4);

    }

    @Test
    public void test2() {
        for (int i = 0; i < 50; i++) {
            DocumentDataDTO documentDataDTO = new DocumentDataDTO();
            documentDataDTO.setName("测试数据，稍后删除" + i);
            documentDataDTO.setRemoteAddr(null);
            documentDataDTO.setLocalAddr(null);
            documentDataDTO.setAuthor("zhanglei");
            documentDataDTO.setAuthorNational("中国");
            documentDataDTO.setIntro("测试数据，稍后删除");
            documentDataDTO.setDformat("test");
            documentDataDTO.setCreateBy("admin");
            documentDataDTO.setDclass(DocumentClassEnum.TEST.getValue());
            documentDataService.saveDocumentData(documentDataDTO);
        }
    }
}
