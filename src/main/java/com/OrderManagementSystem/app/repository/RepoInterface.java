package com.OrderManagementSystem.app.repository;

import java.util.List;

public interface RepoInterface<T, ID> {
    ID save(T entity);
    List<T> findAll();
    T findById(ID id);
    void delete(ID id);
}
