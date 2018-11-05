package com.example.common.base;

import java.util.List;

public interface BaseService<T> {
    T findOne(int key);
    T save(T entity);
    void delete(Object key);
    List<T> findAll();
    void deleteInBatch(Iterable<T> iterable);
    List<T>findAll(Iterable<Integer>iterable);
    List<T>save(Iterable<T>iterable);
}