package com.adam.repository.student;

import com.adam.model.Student;
import com.adam.utils.JPAUtil;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository, Serializable {

    EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();

    @Override
    public void save(Student student) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(student);
        transaction.commit();
    }

    @Override
    public void edit(Student student) {
        entityManager.getTransaction().begin();
        entityManager.merge(student);
        entityManager.getTransaction().commit();
    }

    @Override
    public Student findById(int id) {
        Student student = entityManager.find(Student.class, id);
        return student;
    }

    @Override
    public void delete(int id) {
        Student student = findById(id);
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(student);
        transaction.commit();
    }

    @Override
    public List<Student> getAllStudents() {
        Query query = entityManager.createQuery("SELECT s FROM Student s");
        List<Student> studentList = query.getResultList();
        return studentList;
    }
}
