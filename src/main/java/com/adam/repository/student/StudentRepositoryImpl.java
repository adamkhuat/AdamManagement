package com.adam.repository.student;

import com.adam.model.Student;
import com.adam.utils.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public boolean save(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(student);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean edit(Student student) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(student);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Student findById(int id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public boolean delete(int id) {
        try {
            Student student = findById(id);
            entityManager.getTransaction().begin();
            entityManager.remove(student);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        Query query = entityManager.createQuery("SELECT s FROM Student s");
        List<Student> studentList = query.getResultList();
        return studentList;
    }
}
