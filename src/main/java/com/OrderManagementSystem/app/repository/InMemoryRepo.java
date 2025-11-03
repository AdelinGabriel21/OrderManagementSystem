package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ModelInterface;
import com.OrderManagementSystem.app.util.Ids;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepo<T extends ModelInterface> implements RepoInterface<T, String> {

    private final Map<String, T> storage = new HashMap<>();


    @Override
    public void save(T entity) {
        if (entity.getId() == null || entity.getId().isEmpty()) {
            entity.setId(Ids.createId());
        }
        storage.put(entity.getId(), entity);
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
