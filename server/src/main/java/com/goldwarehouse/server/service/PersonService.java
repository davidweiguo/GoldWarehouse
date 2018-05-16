package com.goldwarehouse.server.service;

import com.goldwarehouse.server.entity.Person;

import java.util.List;

/**
 * Created by David on 2017/11/15.
 */
public interface PersonService {
    void add(Person person);

    List<Person> queryPersons();
}
