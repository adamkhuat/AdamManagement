package com.adam.repository.clazz;

import com.adam.model.Clazz;

import java.util.List;

public interface ClazzRepository {
    List<Clazz> getAllClazz();
    void save(Clazz clazz);
    void update(Clazz clazz);
    void delete(int clazzId);
    Clazz findClazzById(int clazzId);
}
