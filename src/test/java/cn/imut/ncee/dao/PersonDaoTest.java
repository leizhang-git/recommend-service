package cn.imut.ncee.dao;

import cn.imut.ncee.entity.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author zhanglei
 * @Date 2021/2/23 16:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class PersonDaoTest {

    @Autowired
    private PersonDao personDao;

    @Test
    public void register() {
        Person person = new Person("vip.p@live.com","张磊", "zhanglei123");
        boolean isSuccess = personDao.register(person);
        System.out.println(isSuccess);
    }

    @Test
    public void login() {
        String admin = personDao.login("vip.p@live.com");
        System.out.println(admin);
    }

    @Test
    public void updatePerson() {
        Person person = new Person("vip.p@live.com","张磊", "zhanglei123");
        boolean isSuccess = personDao.updatePerson(person.getId(), person);
        System.out.println(isSuccess);
    }

    @Test
    public void updatePersonScore() {
        boolean isSuccess = personDao.updateScore(555, "vip.p@live.com");
        System.out.println(isSuccess);
    }

    @Test
    public void deletePerson() {
        boolean isSuccess = personDao.deletePerson("vip.p@live.com");
        System.out.println(isSuccess);
    }

    @Test
    public void selectAllPerson() {
        List<Person> people = personDao.selectAllPerson();
        people.forEach(System.out::println);
    }

    @Test
    public void selectByIdPerson() {
        Person person = personDao.selectByIdPerson("0074157175b311ebb53a52540099a41f");
        System.out.println(person.toString());
    }
}