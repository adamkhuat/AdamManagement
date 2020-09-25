package com.adam.repository.clazz;

import com.adam.model.Clazz;

import java.util.List;

public interface ClazzRepository {
    List<Clazz> getAllClazz();

    boolean save(Clazz clazz);

    boolean update(int id);

    boolean delete(int clazzId);

    Clazz findClazzById(int clazzId);

}
