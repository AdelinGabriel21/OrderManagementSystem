package com.OrderManagementSystem.app.repository;

import com.OrderManagementSystem.app.model.ServiceEntity;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceEntityRepository extends InFileRepo<ServiceEntity> {
    public ServiceEntityRepository (){
        super("serviceEntity.json", ServiceEntity.class);
    }
}
