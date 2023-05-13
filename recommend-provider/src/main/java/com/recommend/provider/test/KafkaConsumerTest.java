package com.recommend.provider.test;

import ch.qos.logback.classic.LoggerContext;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @Author zhanglei
 * @Date 2023/5/122133
 */
public class KafkaConsumerTest {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumerTest.class);

    static {

    }

    public static void main(String[] args) {
        //1.创建连接kafka的properties配置
        Properties props = new Properties();
        //Kafka服务器地址
        props.setProperty("bootstrap.servers", "101.43.227.230:9092");
        //消费者组（可以使用消费者组将若干消费者组织到一起），共同消费Kafka中的topic的数据
        props.setProperty("group.id", "test-Q");
        //自动提交offset
        props.setProperty("enable.auto.commit", "true");
        //自动提交offset的时间间隔
        props.setProperty("auto.commit.interval.ms", "1000");
        //以字符串方式进行序列化
        props.setProperty("key.deserializer", StringDeserializer.class.getName());
        props.setProperty("value.deserializer", StringDeserializer.class.getName());

        //2.创建kafka消费者
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);

        //3.订阅主题
        kafkaConsumer.subscribe(Collections.singletonList("test-Q"));

        //4.从topic中获取数据
        while (true) {
            //kafka消费者一次拉去一批数据
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                //主题
                String topic = consumerRecord.topic();
                //offset
                long offset = consumerRecord.offset();
                //key
                String key = consumerRecord.key();
                //value
                String value = consumerRecord.value();

                System.out.println("====================================================================================topic: " + topic + " offset: " + offset + " key: " + key + " value: " + value);
            }
        }
    }
}
