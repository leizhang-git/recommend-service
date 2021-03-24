package cn.imut.ncee.service.impl;

import cn.imut.ncee.dao.PersonDao;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.service.PersonService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:04
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public boolean register(Person person) {
        String s = personDao.selectId(person.getId());
        if(s == null) {
            return personDao.register(person);
        }else {
            return false;
        }
    }

    @Override
    public boolean login(String personId, String personPassword) {
        String password = personDao.login(personId);
        return password.equals(personPassword);
    }

    @Override
    public boolean updatePerson(Person person) {
        return false;
    }

    @Override
    public List<Person> selectAllPerson(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return personDao.selectAllPerson();
    }

    @Override
    public Person selectByIdPerson(String personId) {
        return null;
    }
}
