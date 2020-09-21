package com.adam.repository.studentClazz;

import com.adam.model.Student;
import com.adam.model.StudentClass;
import com.adam.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class StudentClassRepositoryImpl implements StudentClazzRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
    private final String GET_LIST_STUDENT = "SELECT sc.student" + " FROM StudentClass sc" + " WHERE sc.clazz.id = :id";

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
    public boolean update(StudentClass studentClass) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(studentClass);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
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
        StudentClass studentClass = entityManager.find(StudentClass.class, id);
        return studentClass;
    }

    @Override
    public List<Student> getListStudent(int clazzId) {
        Query query = entityManager.createQuery(GET_LIST_STUDENT);
        query.setParameter("id", clazzId);
        List<Student> studentList = query.getResultList();
        for (Student s : studentList) {
            System.out.println(s.toString());
        }
        return studentList;
    }
}
