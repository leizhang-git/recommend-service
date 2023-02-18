package cn.imut.ncee.config;

import cn.imut.ncee.domain.KafkaMessage;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Auth zhanglei
 * @Date 2023/2/18 22:12
 */
@Component
public class KafkaCustomer {

    @Resource
    private KafkaMessagePool kafkaMessagePool;

    @KafkaListener(topics = {"kafka.consumer.topic"})
    public void listen(ConsumerRecord<String,String> consumer) {
        String topic = consumer.topic();
        String key = consumer.key();
        String value = consumer.value();

        KafkaMessage kafkaMessage = new KafkaMessage();
        kafkaMessage.setTopic(topic);
        kafkaMessage.setKey(key);
        kafkaMessage.setData(value);

        kafkaMessagePool.sendMessages(kafkaMessage);
    }
}
