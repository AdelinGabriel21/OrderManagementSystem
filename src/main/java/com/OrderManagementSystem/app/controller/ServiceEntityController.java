package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.service.ServiceEntityService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ServiceEntityController {
    private final ServiceEntityService service;

    public ServiceEntityController(ServiceEntityService service) {
        this.service = service;
    }

    public void testAll(){
        ServiceEntity p1 = new ServiceEntity("1", "Cook", Status.valueOf("Active"));
        ServiceEntity p2 = new ServiceEntity("2", "Clean", Status.valueOf("Active"));
        ServiceEntity p3 = new ServiceEntity("3", "Drive", Status.valueOf("Active"));
        service.addService(p1);
        service.addService(p2);
        service.addService(p3);

        List<ServiceEntity> allServices = service.getAllServices();
        System.out.println("All products in repository:");
        for (ServiceEntity s : allServices) {
            System.out.println(s);
        }

        ServiceEntity fetched = service.getServiceById("2");
        System.out.println("Fetched product with ID 2: " + fetched);

        service.deleteService("3");
        System.out.println("After deleting ID 3:");
        for (ServiceEntity s : allServices) {
            System.out.println(s);
        }

        System.out.println("ServiceEntityService test completed.");
    }
}
