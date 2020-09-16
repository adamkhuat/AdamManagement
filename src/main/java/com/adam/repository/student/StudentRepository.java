package com.adam.repository.student;

import com.adam.model.Student;

import java.util.List;

public interface StudentRepository {

    boolean save(Student student);

    boolean edit(Student student);

    Student findById(int id);

    boolean delete(int id);

    List<Student> getAllStudents();
}
