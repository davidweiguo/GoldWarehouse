package com.goldwarehouse.server.dao.impl;

import com.goldwarehouse.server.dao.PersonDao;
import com.goldwarehouse.server.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Created by David on 2017/11/15.
 */
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void add(Person person) {
        em.persist(person);
    }

    @Override
    public List<Person> queryPersons() {
        CriteriaQuery<Person> criteriaQuery = em.getCriteriaBuilder().createQuery(Person.class);
        criteriaQuery.from(Person.class);
        return em.createQuery(criteriaQuery).getResultList();
    }
}
