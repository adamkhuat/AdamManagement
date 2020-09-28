package com.adam.repository.studentClazz;

import com.adam.model.StudentClass;
import com.adam.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class StudentClassRepositoryImpl implements StudentClazzRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public boolean save(StudentClass studentClass) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(studentClass);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    @Override
    public boolean update(int id) {
        try {
            StudentClass studentClass = findStudentInTheClassById(id);
            entityManager.getTransaction().begin();
            entityManager.merge(studentClass);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("LOI UPDATE " + e);
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(findStudentInTheClassById(id));
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public StudentClass findStudentInTheClassById(int id) {
        return entityManager.find(StudentClass.class, id);
    }

}
