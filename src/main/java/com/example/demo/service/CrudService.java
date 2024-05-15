package com.example.demo.service;

import java.util.List;
import java.util.Optional;

/**
 * "CRUD" 是 "Create, Read, Update, Delete" 的缩写，指的是对数据的基本操作：创建、读取、更新和删除。
 * @param <T>
 * @param <ID>
 */
public interface CrudService<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    T save(T entity);

    T update(T entity);

    void deleteById(ID id);
}
