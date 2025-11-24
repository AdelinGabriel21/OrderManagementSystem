package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ModelInterface;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InFileRepo<T extends ModelInterface> implements RepoInterface<T, String> {

    private final File file;
    private final ObjectMapper mapper;
    private final Class<T> type;

    public InFileRepo(String filename, Class<T> type) {
        this.file = new File("src/main/java/com/OrderManagementSystem/app/files/" + filename);
        this.type = type;
        this.mapper = new ObjectMapper();

        try {
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<T>());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void save(T entity) {
        try {
            List<T> all = findAll();
            if (entity.getId() == null || entity.getId().isEmpty()) {
                entity.setId(java.util.UUID.randomUUID().toString());
            }
            all.removeIf(e -> e.getId().equals(entity.getId()));
            all.add(entity);
            mapper.writeValue(file, all);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> findAll() {
        try {
            if (file.length() == 0) return new ArrayList<>();
            return mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, type));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public T findById(String id) {
        return findAll().stream().filter(e -> e.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void delete(String id) {
        try {
            List<T> all = findAll();
            all.removeIf(e -> e.getId().equals(id));
            mapper.writeValue(file, all);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
