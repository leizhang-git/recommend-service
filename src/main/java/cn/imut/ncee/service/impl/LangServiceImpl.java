package cn.imut.ncee.service.impl;

import cn.hutool.core.text.UnicodeUtil;
import cn.imut.ncee.context.IContextInfoProxy;
import cn.imut.ncee.exception.ErrCode;
import cn.imut.ncee.exception.StrException;
import cn.imut.ncee.exception.SystemException;
import cn.imut.ncee.service.LangService;
import cn.imut.ncee.util.FileUtil;
import cn.imut.ncee.util.LangUtil;
import cn.imut.ncee.util.SpringContextHolder;
import org.apache.commons.compress.utils.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
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

    @Autowired
    private ExecutorService commonThreadPoolExecutor;

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
        List<String> langList = Lists.newArrayList();
        String filePath = LANG_PATH + LANG_FILE_NAME;
        try {
            //读取文件
            FileUtil.getFileContent(filePath, langList);
            //解析
            Map<String, String> langMap = langList.stream().collect(Collectors.toMap(l -> l.split("=")[0], l -> UnicodeUtil.toString(l.split("=")[1]), (e1, e2) -> e1));

            List<String> zhCNList = Lists.newArrayList();
            List<String> zhTWList = Lists.newArrayList();
            List<String> enUSList = Lists.newArrayList();
            //处理zh_CN
            for (Map.Entry<String, String> stringEntry : langMap.entrySet()) {
                //MESSAGE_zh_CN.properties
                zhCNList.add(stringEntry.getKey() + "=" + stringEntry.getValue());
                //MESSAGE_en_US.properties
                enUSList.add(stringEntry.getKey() + "=" + langUtil.chineseToEng(stringEntry.getValue()));
                //MESSAGE_zh_TW.properties
                zhTWList.add(stringEntry.getKey() + "=" + langUtil.chineseToCht(stringEntry.getValue()));
            }

            //写入文件
            try {
                FileUtil.writeFileContext(zhCNList, LANG_PATH + "MESSAGE_zh_CN.properties");
                FileUtil.writeFileContext(zhTWList, LANG_PATH + "MESSAGE_zh_TW.properties");
                FileUtil.writeFileContext(enUSList, LANG_PATH + "MESSAGE_en_US.properties");
            } catch (Exception e) {
                log.error("写入文件失败", e);
                throw new StrException("写入文件失败.");
            }
        } catch (IOException e) {
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
