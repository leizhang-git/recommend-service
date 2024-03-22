package com.recommend.bootstrap.service.impl;

import cn.hutool.core.text.UnicodeUtil;
import com.recommend.bootstrap.service.LangService;
import com.recommend.bootstrap.util.LangUtil;
import com.recommend.consumer.context.IContextInfoProxy;
import com.recommend.consumer.exception.ErrCode;
import com.recommend.consumer.exception.SystemException;
import com.recommend.provider.util.SpringContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @Auth zhanglei
 * @Date 2023/3/13 18:24
 */
@Service
public class LangServiceImpl implements LangService {

    private static final String LANG_PATH = "src/main/resources/lang/";

    private static final String LANG_FILE_NAME = "MESSAGE.properties";

    private static final Logger log = LoggerFactory.getLogger(LangServiceImpl.class);

    @Resource
    private ThreadPoolTaskExecutor commonThreadPoolExecutor;

    /**
     * 自动同步多语工具类
     * 读取MESSAGE.properties文件，调百度翻译接口进行翻译后自动同步至以下三个多语文件：
     *      MESSAGE_en_US.properties
     *      MESSAGE_zh_TW.properties
     *      MESSAGE_zh_CN.properties
     */
    @Override
    public void syncLang() {
        LangUtil langUtil = SpringContextHolder.getBean(LangUtil.class);
        String filePath = LANG_PATH + LANG_FILE_NAME;
        try {
            // 读取文件
            List<String> langList = Files.readAllLines(Paths.get(filePath));
            // 解析
            Map<String, String> langMap = langList.stream()
                    .collect(Collectors.toMap(l -> l.split("=")[0], l -> UnicodeUtil.toString(l.split("=")[1]), (e1, e2) -> e1));
            List<String> zhCNList = new ArrayList<>();
            List<String> zhTWList = new ArrayList<>();
            List<String> enUSList = new ArrayList<>();
            // 处理 zh_CN
            for (Map.Entry<String, String> stringEntry : langMap.entrySet()) {
                // MESSAGE_zh_CN.properties
                zhCNList.add(stringEntry.getKey() + "=" + stringEntry.getValue());
                // MESSAGE_en_US.properties
                enUSList.add(stringEntry.getKey() + "=" + langUtil.chineseToEng(stringEntry.getValue()));
                // MESSAGE_zh_TW.properties
                zhTWList.add(stringEntry.getKey() + "=" + langUtil.chineseToCht(stringEntry.getValue()));
            }
            // 写入文件
            Files.write(Paths.get(LANG_PATH + "MESSAGE_zh_CN.properties"), zhCNList);
            Files.write(Paths.get(LANG_PATH + "MESSAGE_zh_TW.properties"), zhTWList);
            Files.write(Paths.get(LANG_PATH + "MESSAGE_en_US.properties"), enUSList);
        } catch (IOException e) {
            log.error("读取或写入文件失败", e);
            throw new SystemException(ErrCode.SYS_ZOOK_CREATE_ERROR);
        }
    }

    private Future<?> execute(Callable<?> loader){
        return commonThreadPoolExecutor.submit(()->{
            Object result;
            try {
                result = loader.call();
            }catch (Exception e){
                result = null;
            }finally {
                IContextInfoProxy.reset();
            }
            return result;
        });
    }
}
