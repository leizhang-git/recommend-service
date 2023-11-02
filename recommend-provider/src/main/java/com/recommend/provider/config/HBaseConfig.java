//package com.recommend.provider.config;
//
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.client.Connection;
//import org.apache.hadoop.hbase.client.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.IOException;
//
///**
// * @Describe
// * @Author zhanglei
// * @Date 2023/7/28 17:25
// */
//@Configuration
//public class HBaseConfig {
//
//    @Value("${hbase.zookeeper.quorum}")
//    private String zookeeperQuorum;
//
//    @Value("${hbase.zookeeper.property.clientPort}")
//    private String clientPort;
//
//    @Bean
//    public org.apache.hadoop.conf.Configuration configuration() {
//        org.apache.hadoop.conf.Configuration config = HBaseConfiguration.create();
//        config.set("hbase.zookeeper.quorum", zookeeperQuorum);
//        config.set("hbase.zookeeper.property.clientPort", clientPort);
//        return config;
//    }
//
//    @Bean
//    public Connection connection() throws IOException {
//        return ConnectionFactory.createConnection(configuration());
//    }
//
//}
