package cn.imut.ncee.service.impl;

import cn.imut.ncee.algorithm.RecommendAlgorithm;
import cn.imut.ncee.dao.MessageBoardDao;
import cn.imut.ncee.dao.PersonDao;
import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.PersonService;
import cn.imut.ncee.utils.MD5Utils;
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

    @Autowired
    private MessageBoardDao messageBoardDao;

    @Autowired
    private RecommendAlgorithm recommendAlgorithm;

    @Override
    public boolean register(Person person) {
        if(person.getId().length() != 0 && person.getName().length() != 0 && person.getPassword().length() != 0) {
            String s = personDao.selectId(person.getId());
            if(s == null) {
                String md5Password = MD5Utils.stringMD5(person.getPassword());
                String password = MD5Utils.convertMD5(md5Password);
                person.setPassword(password);
                return personDao.register(person);
            }else {
                return false;
            }
        }
        return false;
    }

    @Override
    public List<Person> login(String personId, String personPassword) {
        String password = personDao.login(personId);
        if(password != null) {
            String md5 = MD5Utils.convertMD5(password);
            if(md5.equals(MD5Utils.stringMD5(personPassword))) {
                return personDao.selectByIdPerson(personId);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean updatePerson(Person person, String id) {
        return personDao.updatePerson(id, person);
    }

    @Override
    public List<Person> selectAllPerson(String id) {
        if(id == null || id.length() == 0) {
            return personDao.selectAllPerson();
        }else {
            return personDao.selectByIdPerson(id);
        }
    }

    @Override
    public List<Person> selectByIdPerson(String personId) {
        return personDao.selectByIdPerson(personId);
    }

    @Override
    public boolean deletePerson(String id) {
        return personDao.deletePerson(id);
    }

    @Override
    public Object voluntary(AlgorithmIndex index) {
        return recommendAlgorithm.majorRecommend(index);
    }

    @Override
    public boolean addMessage(MessageBoard messageBoard) {
        return messageBoardDao.addMessage(messageBoard);
    }
}
