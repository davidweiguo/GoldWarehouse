package com.goldwarehouse.server.service.impl;

import com.goldwarehouse.server.dao.PersonDao;
import com.goldwarehouse.server.entity.Person;
import com.goldwarehouse.server.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by David on 2017/11/15.
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Transactional
    @Override
    public void add(Person person) {
        personDao.add(person);
    }
    
    @Override
    public List<Person> queryPersons() {
        return personDao.queryPersons();
    }
}
