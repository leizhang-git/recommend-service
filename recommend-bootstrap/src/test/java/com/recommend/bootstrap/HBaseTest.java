//package com.recommend.bootstrap;
//
//import com.recommend.provider.util.HBaseUtil;
//import org.apache.hadoop.hbase.client.Result;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Describe 测试HBase工具类
// * @Author zhanglei
// * @Date 2023/7/31 13:34
// */
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class HBaseTest {
//
//    private final static Logger log = LoggerFactory.getLogger(HBaseTest.class);
//
//    @Autowired
//    private HBaseUtil hbaseUtil;
//
//    @Test
//    public void testHBaseService() throws IOException {
//        //获取所有namespace
//        List<String> allNameSpace = hbaseUtil.listNameSpace();
//        System.out.println(allNameSpace);
//
//        //判断表是否存在
//        Boolean exists = hbaseUtil.tableExists("default", "demo");
//        log.info(">>>>>{}", exists);
//
//        //创建表
//        List<String> result = new ArrayList<>();
//        result.add("cloumnfamily1");
//        result.add("cloumnfamily2");
//        hbaseUtil.createTable("zl-test-table", result);
//
//        //获取所有表名
//        System.out.println(hbaseUtil.listTableName());
//
//        //获取所有表信息
//        System.out.println(hbaseUtil.listTable());
//
//        //删除命名空间
////        hbaseService.deleteNameSpace("zl-test");
////        System.out.println(hbaseService.listNameSpace());
//
//        //插入数据
//        hbaseUtil.putData("user", "rowkey_100", "userInfo", "name", "zhangsan");
//
//        //删除表
//        hbaseUtil.truncateTable("zl-test-table");
//        System.out.println(hbaseUtil.listTableName());
//
//        //删除指定列
//        hbaseUtil.deleteData("user", "rowkey_16", "base_info", "birthday");
//
//        //删除指定列族
//        hbaseUtil.deleteFamily("user", "rowkey_16", "base_info");
//
//        hbaseUtil.deleteRow("user", "rowkey_10");
//
//        //获取单行或多行数据
//        System.out.println(hbaseUtil.getRowData("user", "rowkey_16"));
//
//        //获取单元格数据
//        List<Result> rowData = hbaseUtil.getRowData("user", "rowkey_16");
//        for (Result rowDatum : rowData) {
//            System.out.println(hbaseUtil.getCallData(rowDatum));
//        }
//    }
//}
