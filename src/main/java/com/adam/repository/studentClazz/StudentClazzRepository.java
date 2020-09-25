package com.adam.repository.studentClazz;

import com.adam.model.StudentClass;


public interface StudentClazzRepository {
    boolean save(StudentClass studentClass);

    boolean update(int id);

    boolean delete(int id);

    StudentClass findStudentInTheClassById(int id);

}
