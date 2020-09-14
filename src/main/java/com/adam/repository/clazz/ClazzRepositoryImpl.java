package com.adam.repository.clazz;

import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.utils.JPAUtil;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ClazzRepositoryImpl implements ClazzRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public List<Clazz> getAllClazz() {
        Query query = entityManager.createQuery("SELECT c FROM Clazz c");
        List<Clazz> clazzList = query.getResultList();
        return clazzList;

//        Query query = entityManager.createNamedQuery("getAllClazz");
//        List<Clazz> clazzList = query.getResultList();
//        return clazzList;
    }

    @Override
    public void save(Clazz clazz) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(clazz);
        transaction.commit();
    }

    @Override
    public void update(Clazz clazz) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(clazz);
        transaction.commit();
    }

    @Override
    public void delete(int clazzId) {
        Clazz clazz = findClazzById(clazzId);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(clazz);
        transaction.commit();
    }

    @Override
    public Clazz findClazzById(int clazzId) {
        Clazz clazz = entityManager.find(Clazz.class, clazzId);
        return clazz;
    }
}
