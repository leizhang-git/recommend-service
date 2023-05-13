package com.recommend.provider.test;

import com.google.common.collect.Lists;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Author zhanglei
 * @Date 2023/5/112317
 */
public class KafKaProducerTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1.创建连接kafka的properties配置
        Properties props = new Properties();
        //Kafka服务器地址
        props.put("bootstrap.servers", "101.43.227.230:9092");
        //acks表示生产者生产数据到kafka中，kafka中以什么样的策略返回
        //0：生产者不会等待kafka响应，1：kafka会把消息写道本地日志文件中，不会等待集群中其他机器响应，all：leader会等所有follower同步完成
        props.put("acks", "all");
        //以字符串方式进行序列化
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        //新建一个topic
        //创建管理者
//        AdminClient adminClient = KafkaAdminClient.create(props);
//        try {
//            Set<String> strings = adminClient.listTopics().names().get();
//            if(strings.contains("test-Q")) {
//                return;
//            }
//            NewTopic newTopic = new NewTopic("test-Q", 3, (short) 1);
//            CreateTopicsResult result = adminClient.createTopics(Lists.newArrayList(newTopic));
//            result.all().get();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            adminClient.close();
//        }

        //2.创建一个生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        //3.发送消息到指定Topic
        for (int i = 0; i < 100; i++) {
            //1.同步方式发送消息
            //构建一条消息
//            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test-Q", null, i + "");
//            Future<RecordMetadata> future = kafkaProducer.send(producerRecord);
//            future.get();
//            System.out.println("第" + i + "条消息写入成功！");
            //2.异步回调发送消息
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test-Q", null, i + "");
            kafkaProducer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //1.判断发送消息是否成功
                    if(e != null) {
                        System.out.println("生产消息出现异常！");
                        System.out.println(e.getMessage());
                        System.out.println(Arrays.toString(e.getStackTrace()));
                    }else {
                        String topic = recordMetadata.topic();
                        int partition = recordMetadata.partition();
                        long offset = recordMetadata.offset();
                        System.out.println("topic: " + topic + "partition: " + partition + "offset: " + offset);
                    }
                }
            });
        }
        //4.关闭生产者
        kafkaProducer.close();

    }
}
