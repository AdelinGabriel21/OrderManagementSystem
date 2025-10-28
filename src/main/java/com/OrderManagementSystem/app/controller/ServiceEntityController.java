package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.model.Status;
import com.OrderManagementSystem.app.service.ServiceEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ServiceEntityController {

    private final ServiceEntityService service;

    public ServiceEntityController(ServiceEntityService service) {
        this.service = service;
    }

    @GetMapping("/test-services")
    @ResponseBody
    public String testServices() {
        ServiceEntity s1 = new ServiceEntity("1", "Cook", Status.ACTIVE);
        ServiceEntity s2 = new ServiceEntity("2", "Clean", Status.ACTIVE);
        ServiceEntity s3 = new ServiceEntity("3", "Drive", Status.ACTIVE);

        service.addService(s1);
        service.addService(s2);
        service.addService(s3);

        List<ServiceEntity> allServices = service.getAllServices();

        StringBuilder sb = new StringBuilder("All services in repository:<br>");
        allServices.forEach(se -> sb.append(se).append("<br>"));

        ServiceEntity fetched = service.getServiceById("2");
        sb.append("<br>Fetched service with ID 2: ").append(fetched).append("<br>");

        service.deleteService("3");
        sb.append("<br>After deleting service with ID 3:<br>");
        service.getAllServices().forEach(se -> sb.append(se).append("<br>"));

        sb.append("<br>ServiceEntityService test completed.");

        return sb.toString();
    }
}
