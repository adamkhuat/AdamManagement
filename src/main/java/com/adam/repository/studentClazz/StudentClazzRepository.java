package com.adam.repository.studentClazz;

import com.adam.model.Student;
import com.adam.model.StudentClass;

import java.util.List;

public interface StudentClazzRepository {
    boolean save(StudentClass studentClass);
    boolean update(StudentClass studentClass);
    boolean delete(int id);
    List<StudentClass> getAllStudentOfTheClass(int clazzId);
    StudentClass findStudentInTheClassById(int id);
    List<Student> getListStudent(int clazzId);
}
