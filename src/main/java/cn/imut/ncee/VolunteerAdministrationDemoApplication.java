package cn.imut.ncee;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.imut.ncee.dao")
@SpringBootApplication()
public class VolunteerAdministrationDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolunteerAdministrationDemoApplication.class, args);
    }
}
