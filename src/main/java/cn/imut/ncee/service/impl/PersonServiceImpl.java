package cn.imut.ncee.service.impl;

import cn.imut.ncee.algorithm.RecommendAlgorithm;
import cn.imut.ncee.dao.MessageBoardDao;
import cn.imut.ncee.dao.PersonDao;
import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.service.PersonService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

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
        if(password != null) {
            return password.equals(personPassword);
        }
        return false;
    }

    @Override
    public boolean updatePerson(Person person, String id) {
        return personDao.updatePerson(id, person);
    }

    @Override
    public List<Person> selectAllPerson(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        return personDao.selectAllPerson();
    }

    @Override
    public Person selectByIdPerson(String personId) {
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
