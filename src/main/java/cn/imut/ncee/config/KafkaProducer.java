package cn.imut.ncee.config;

import cn.imut.ncee.domain.KafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:12
 */
@Configuration
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Autowired
    private KafkaMessagePool kafkaMessagePool;

    private boolean isExit;

    @Value("${kafka.producer.topic}")
    private String producerTopic;


    @PostConstruct
    public void init() {
        isExit = false;
    }

    public void send() {
        while (!isExit) {
            KafkaMessage messages = kafkaMessagePool.getMessages();
            if(messages != null) {
                kafkaTemplate.send(producerTopic,messages.getKey(),messages.getData());
            }
        }
    }

    public void close() {
        this.isExit = true;
    }
}
