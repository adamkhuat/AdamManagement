package com.adam.repository;

import com.adam.model.Student;

import java.util.List;

public interface StudentRepository {
    void save(Student student);
    void edit(Student student);
    Student findById(int id);
    void delete(int id);
    List<Student> getAllStudents();
}
