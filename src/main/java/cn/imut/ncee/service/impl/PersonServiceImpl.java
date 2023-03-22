package cn.imut.ncee.service.impl;

import cn.imut.ncee.algorithm.RecommendAlgorithm;
import cn.imut.ncee.dao.MessageBoardDao;
import cn.imut.ncee.dao.PersonDao;
import cn.imut.ncee.entity.pojo.AlgorithmIndex;
import cn.imut.ncee.entity.pojo.Person;
import cn.imut.ncee.entity.vo.MessageBoard;
import cn.imut.ncee.exception.ErrCode;
import cn.imut.ncee.exception.StrException;
import cn.imut.ncee.exception.SystemException;
import cn.imut.ncee.service.PersonService;
import cn.imut.ncee.util.AESUtil;
import cn.imut.ncee.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 * @Author zhanglei
 * @Date 2021/2/23 9:04
 */
@Service
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonDao personDao;

    @Autowired
    private MessageBoardDao messageBoardDao;

    @Autowired
    private JWTServiceImpl jwtService;

    @Autowired
    private RecommendAlgorithm recommendAlgorithm;

    @Override
    public boolean register(Person person) {
        if(null == person) {
            throw new StrException("用户为null.");
        }
        //兼容下前端没有传id的情况
        if(StringUtils.isBlank(person.getId())) {
            person.setId(UUID.randomUUID().toString());
        }
        if(StringUtils.isBlank(person.getName())) {
            throw new StrException("用户名为空~注册失败.");
        }
        if(StringUtils.isBlank(person.getPassword())) {
            throw new StrException("密码为空~注册失败.");
        }

        String s = personDao.selectId(person.getId());
        if(StringUtils.isNotBlank(s)) {
            throw new StrException("当前用户已经存在,请勿重复注册");
        }
        return personDao.register(person);
    }

    @Override
    public List<Person> login(String personId, String personPassword, HttpServletResponse response) {
        String password = personDao.login(personId);
        if(StringUtils.isNotBlank(password)) {
            if(!password.equals(personPassword)) {
                throw new SystemException(ErrCode.SYS_PASSWORD_ERROR);
            }
            String token = jwtService.createToken(personId, "", "");
            log.info("token is {}", token);
            response.setHeader("OS-token", token);
            return personDao.selectByIdPerson(personId);
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

    @Override
    public boolean update(String oldP, String newP, String id) {
        String password = personDao.login(id);
        String newPass = MD5Util.convertMD5(password);
        String oldPass = MD5Util.stringMD5(oldP);
        if(newPass.equals(oldPass)) {
            String md5 = MD5Util.stringMD5(newP);
            String pass = MD5Util.convertMD5(md5);
            return personDao.update(pass, id);
        }
        return false;
    }
}
