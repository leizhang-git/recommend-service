package cn.imut.ncee.config.i18n;

import cn.imut.ncee.util.SpringContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LocaleMessage {

    public static String getMessage(String code) {
        return MessageUtils.getMessage(code);
    }
}
