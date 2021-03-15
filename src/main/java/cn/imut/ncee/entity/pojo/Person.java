package cn.imut.ncee.entity.pojo;

import lombok.*;

/**
 * 用户信息
 *
 * @Author zhanglei
 * @Date 2021/1/27 17:11
 */
@Getter
@Setter
public class Person {

    /**
     * 用户Id
     */
    private String id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户成绩
     */
    private String score;

    /**
     * 用户登陆密码
     */
    private String password;

    /**
     * 用户角色
     */
    private String role;


    public Person() {

    }

    public Person(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Person(String id, String name, String score, String password) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
