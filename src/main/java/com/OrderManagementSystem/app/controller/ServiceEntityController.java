package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.service.ServiceEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/services")
public class ServiceEntityController {

    private final ServiceEntityService service;

    public ServiceEntityController(ServiceEntityService service) {
        this.service = service;
    }

    @GetMapping
    public String listServices(Model model) {
        model.addAttribute("services", service.getAllServices());
        return "service/index";
    }

    @GetMapping("/new")
    public String newServiceForm(Model model) {
        model.addAttribute("serviceEntity", new ServiceEntity());
        return "service/form";
    }

    @PostMapping
    public String addService(@ModelAttribute ServiceEntity serviceEntity) {
        service.saveService(serviceEntity);
        return "redirect:/services";
    }

    @PostMapping("/{id}/delete")
    public String deleteService(@PathVariable String id) {
        service.deleteService(id);
        return "redirect:/services";
    }
}
