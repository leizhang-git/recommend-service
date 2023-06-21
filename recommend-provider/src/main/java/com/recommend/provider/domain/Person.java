package com.recommend.provider.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Describe 用于序列化测试
 * @Author zhanglei
 * @Date 2023/6/19 14:36
 */
@Data
public class Person implements Serializable {

    public Person() {
    }

    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;

    private Integer i1;
    private Integer i2;
    private Integer i3;
    private Integer i4;
    private Integer i5;
    private Integer i6;
    private Integer i7;
    private Integer i8;
    private Integer i9;
    private Integer i10;

    private List<String> l1;
    private List<String> l2;
    private List<String> l3;
}
