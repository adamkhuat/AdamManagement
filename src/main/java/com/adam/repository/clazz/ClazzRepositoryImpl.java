package com.adam.repository.clazz;

import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ClazzRepositoryImpl implements ClazzRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public List<Clazz> getAllClazz() {
        Query query = entityManager.createNamedQuery("getAllClazz");
        List<Clazz> clazzList = query.getResultList();
        return clazzList;
    }

    @Override
    public boolean save(Clazz clazz) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(clazz);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Clazz clazz) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(clazz);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(int clazzId) {
        try {
            Clazz clazz = findClazzById(clazzId);
            entityManager.getTransaction().begin();
            entityManager.remove(clazz);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Clazz findClazzById(int clazzId) {
        Clazz clazz = entityManager.find(Clazz.class, clazzId);
        return clazz;
    }

    @Override
    public List<Student> getListStudentByClazzId(int clazzId) {
        Query query = entityManager.createQuery("");
        return null;
    }

}
