package com.recommend.provider.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Describe
 * @Author zhanglei
 * @Date 2023/7/28 17:22
 */
@Component
public class HBaseUtil {
    @Resource
    private Connection connection;

    private Admin admin;

    @Bean
    public void initial() throws IOException {
        admin = connection.getAdmin();
    }


    /**
     * 创建命名空间
     * @param nameSpace 名称
     * @throws IOException
     */
    public void createNameSpace(String nameSpace) throws IOException {
        admin.createNamespace(NamespaceDescriptor.create(nameSpace).build());
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return true or false
     * @throws IOException
     */
    public Boolean tableExists(String tableName) throws IOException {
        if(StrUtil.isBlank(tableName)) {
            throw new IllegalArgumentException("tableName is empty.");
        }
        TableName name = TableName.valueOf(tableName);
        return admin.tableExists(name);
    }

    /**
     * 判断表是否存在(带namespace)
     * @param namespace
     * @param tableName
     * @return true or false
     * @throws IOException
     */
    public Boolean tableExists(String namespace, String tableName) throws IOException {
        if(StrUtil.isBlank(tableName)) {
            throw new IllegalArgumentException("tableName is empty.");
        }
        if(StrUtil.isBlank(namespace)) {
            return tableExists(tableName);
        }
        TableName name = TableName.valueOf(namespace, tableName);
        return admin.tableExists(name);
    }

    /**
     * 创建表
     * @param tableName name
     * @param columnDesc 列簇描述
     * @throws IOException
     */
    public void createTable(String tableName, List<String> columnDesc) throws IOException {
        if(tableExists(tableName)) {
            return;
        }
        TableName name = TableName.valueOf(tableName);
        HTableDescriptor tableDescriptor = new HTableDescriptor(name);
        if(CollUtil.isNotEmpty(columnDesc)) {
            columnDesc.forEach(c -> {
                tableDescriptor.addFamily(new HColumnDescriptor(c));
            });
        }
        admin.createTable(tableDescriptor);
    }

    /**
     * 删除表
     * @param tableName name
     * @throws IOException
     */
    public void truncateTable(String tableName) throws IOException {
        if(!tableExists(tableName)) {
            return;
        }
        TableName name = TableName.valueOf(tableName);
        admin.disableTable(name);
        admin.deleteTable(name);
    }

    /**
     * 获取所有表名
     * @throws IOException
     */
    public List<String> listTableName() throws IOException {
        List<String> result = Lists.newArrayList();
        HTableDescriptor[] tableDescriptor = admin.listTables();
        for (HTableDescriptor hTableDescriptor : tableDescriptor) {
            result.add(hTableDescriptor.getNameAsString());
        }
        return result;
    }

    /**
     * 查看所有表信息(namespace:name)
     * @throws IOException
     */
    public List<String> listTable() throws IOException {
        List<String> result = Lists.newArrayList();
        TableName[] tableNames = admin.listTableNames();
        for (TableName table : tableNames) {
            result.add(table.getNameAsString());
        }
        return result;
    }


    /**
     * 获取所有命名空间
     * @throws IOException
     */
    public List<String> listNameSpace() throws IOException {
        List<String> result = Lists.newArrayList();
        NamespaceDescriptor[] namespaceDescriptors = admin.listNamespaceDescriptors();
        for (NamespaceDescriptor namespaceDescriptor : namespaceDescriptors) {
            result.add(namespaceDescriptor.getName());
        }
        return result;
    }

    /**
     * 删除命名空间
     * @param nameSpace 命名空间
     * @throws IOException
     */
    public void deleteNameSpace(String nameSpace) throws IOException {
        //获取该名称空间下所有的表
        TableName[] names = admin.listTableNamesByNamespace(nameSpace);
        for (TableName name : names) {
            //禁用表
            admin.disableTable(name);
            //删除表
            admin.deleteTable(name);
        }
        //删除名称空间
        admin.deleteNamespace(nameSpace);
    }

    public void getDateByColumn(String tableName, String rowKey, String columnName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        //查询整行数据
        Get get = new Get(rowKey.getBytes());
        //查询某个列的数据
        get.addFamily(columnName.getBytes());
        //获取结果
        Result result = table.get(get);
        List<Cell> cells = result.listCells();
        for (Cell cell : cells) {
            //获取rowkey
            String rowkey = new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            //获取列簇
            String family = new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength());
            //获取列限定符
            String qualifier = new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            //获取值
            if(qualifier.equals("page") ){
                int value = Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println("rowkey="+rowkey+",family="+family+",qualifier="+qualifier+",value="+value);
            }else{
                String value = new String(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                System.out.println("rowkey="+rowkey+",family="+family+",qualifier="+qualifier+",value="+value);
            }
        }
        table.close();
    }

    public void getDateByAllColumn(String tableName) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        //查询整行数据
        Get get = new Get("row1".getBytes());
        //查询某个列的数据

    }

    public String getData(String tableName, String rowKey, String family, String qualifier) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        Result result = table.get(get);
        byte[] value = result.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        return Bytes.toString(value);
    }

    public void putData(String tableName, String rowKey, String family, String qualifier, String value) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowKey));
        put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
        table.put(put);
    }
}
