package com.goldwarehouse.server.dao;

import com.goldwarehouse.server.entity.Person;

import java.util.List;

/**
 * Created by David on 2017/11/15.
 */
public interface PersonDao {
    void add(Person person);

    List<Person> queryPersons();
}
