package com.adam.repository.clazz;

import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.utils.JPAUtil;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ClazzRepositoryImpl implements ClazzRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public List<Clazz> getAllClazz() {
//        Query query = entityManager.createQuery("SELECT cl FROM Clazz cl");
//        List<Clazz> clazzList = query.getResultList();
//        return clazzList;

        Query query = entityManager.createNamedQuery("get all class");
        List<Clazz> clazzList = query.getResultList();
        return clazzList;
    }

    @Override
    public void create() {

    }

    @Override
    public void update(Clazz clazz) {

    }

    @Override
    public void delete(int clazzId) {

    }

    @Override
    public Clazz findClazzById(int clazzId) {
        return null;
    }
}
