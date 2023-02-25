//package cn.imut.ncee.config;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * topic初始化
// * @Auth zhanglei
// * @Date 2023/2/25 23:12
// */
//@Configuration
//public class KafkaConfig {
//
//    /**
//     * 创建一个名为test-consumer-group.test的Topic并设置分区数为8，分区副本数为2
//     */
//    @Bean
//    public NewTopic initialTopic() {
//        return new NewTopic("test-consumer-group.test", 8, (short) 2);
//    }
//
//}
