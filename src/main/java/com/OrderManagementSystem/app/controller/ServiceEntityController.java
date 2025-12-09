package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.ServiceEntity;
import com.OrderManagementSystem.app.service.ServiceEntityService;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        ServiceEntity serviceEntity = service.getServiceById(id);
        if (serviceEntity == null) {
            return "redirect:/services";
        }
        model.addAttribute("serviceEntity", serviceEntity);
        model.addAttribute("statuses", com.OrderManagementSystem.app.model.Status.values());
        return "service/form";
    }

    @GetMapping("/new")
    public String newServiceForm(Model model) {
        model.addAttribute("serviceEntity", new ServiceEntity());
        model.addAttribute("statuses", com.OrderManagementSystem.app.model.Status.values());
        return "service/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        ServiceEntity serviceEntity = service.getServiceById(id);
        if (serviceEntity == null) {
            return "redirect:/services";
        }
        model.addAttribute("serviceEntity", serviceEntity);
        return "service/details";
    }

    @PostMapping
    public String addService(@Valid @ModelAttribute ServiceEntity serviceEntity, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("statuses", com.OrderManagementSystem.app.model.Status.values());
            return "service/form";
        }

        try {
            service.saveService(serviceEntity);
        } catch (ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());

            model.addAttribute("statuses", com.OrderManagementSystem.app.model.Status.values());
            return "service/form";
        }

        return "redirect:/services";
    }

    @PostMapping("/{id}/delete")
    public String deleteService(@PathVariable String id, RedirectAttributes redirectAttributes) {
        try {
            service.deleteService(id);
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addAttribute("error", "in_use");
            return "redirect:/services";
        }
        return "redirect:/services";
    }
}
