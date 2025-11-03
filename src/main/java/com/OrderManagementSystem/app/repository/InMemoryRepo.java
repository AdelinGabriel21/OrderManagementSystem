package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.util.Ids;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepo<T> implements RepoInterface<T, String> {

    private final Map<String, T> storage = new HashMap<>();


    @Override
    public void save(T entity) {
        String id = Ids.createId();
        storage.put(id, entity);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public T findById(String id) {
        return storage.get(id);
    }

    @Override
    public void delete(String id) {
        storage.remove(id);
        System.out.println(storage);
    }
}
