package com.adam.repository.clazz;

import com.adam.model.Clazz;
import com.adam.model.Student;
import com.adam.model.StudentClass;

import java.util.List;

public interface ClazzRepository {
    List<Clazz> getAllClazz();

    boolean save(Clazz clazz);

    boolean update(int id);

    boolean delete(int clazzId);

    Clazz findClazzById(int clazzId);

    List<Student> getListStudentByClazzId(int clazzId);

}
