package cn.imut.ncee.config;

import cn.imut.ncee.domain.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:11
 */
@Component
@Configuration
public class KafkaMessagePool {

    private static Logger log = LoggerFactory.getLogger(KafkaMessagePool.class);

    private Queue<KafkaMessage> messageQueue;

    @PostConstruct
    public void init() {
        messageQueue = new ConcurrentLinkedQueue<>();
    }

    /**
     * 入队
     * @param message
     */
    public void sendMessages(KafkaMessage message) {
        messageQueue.add(message);
    }

    /**
     * 出队
     * @return
     */
    public KafkaMessage getMessages() {
        return messageQueue.poll();
    }
}
