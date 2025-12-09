package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.OrderLineService;
import com.OrderManagementSystem.app.service.OrderService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor; // Import this!
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import java.beans.PropertyEditorSupport;

@Controller
@RequestMapping("/orderLines")
public class OrderLineController {

    private final ProductService productService;
    private final UnitOfMeasureService unitService;
    private final OrderLineService service;
    private final OrderService orderService;

    public OrderLineController(ProductService serviceProduct, UnitOfMeasureService serviceUnit, OrderLineService service, OrderService orderService) {
        this.productService = serviceProduct;
        this.unitService = serviceUnit;
        this.service = service;
        this.orderService = orderService;
    }

    @GetMapping
    public String listOrderLines(Model model) {
        model.addAttribute("orderLines", service.getAllOrderLines());
        return "orderLine/index";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable String id, Model model) {
        OrderLine orderLine = service.getOrderLineById(id);
        if (orderLine == null) {
            return "redirect:/orderLines";
        }
        model.addAttribute("orderLine", orderLine);
        populateDependencies(model);
        return "orderLine/form";
    }

    @GetMapping("/new")
    public String newOrderLineForm(@RequestParam(required = false) String orderID, Model model) {
        OrderLine orderLine = new OrderLine();

        if (orderID != null && !orderID.isEmpty()) {
            Order order = orderService.findOrderById(orderID);
            orderLine.setOrder(order);
        }

        model.addAttribute("orderLine", orderLine);
        populateDependencies(model);

        return "orderLine/form";
    }

    @GetMapping("/details/{id}")
    public String showDetails(@PathVariable String id, Model model) {
        OrderLine orderLine = service.getOrderLineById(id);
        if (orderLine == null) {
            return "redirect:/orderLines";
        }
        model.addAttribute("orderLine", orderLine);
        return "orderLine/details";
    }

    @PostMapping
    public String addOrderLine(@Valid @ModelAttribute OrderLine orderLine,
                               BindingResult bindingResult,
                               Model model) {

        if (bindingResult.hasErrors()) {
            populateDependencies(model);
            return "orderLine/form";
        }

        try {
            service.saveOrderLine(orderLine);
        } catch (jakarta.validation.ValidationException e) {
            bindingResult.reject("globalError", e.getMessage());
            populateDependencies(model);
            return "orderLine/form";
        }

        return "redirect:/orderLines";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrderLine(@PathVariable String id) {
        OrderLine line = service.getOrderLineById(id);
        String orderId = (line != null && line.getOrder() != null) ? line.getOrder().getId() : null;

        service.deleteOrderLine(id);

        if (orderId != null) {
            return "redirect:/orders/details/" + orderId;
        }
        return "redirect:/orderLines";
    }

    private void populateDependencies(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("units", unitService.getAllUnitsOfMeasure());
        model.addAttribute("orders", orderService.findAllOrders());
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

        binder.registerCustomEditor(SellableItem.class, "item", new PropertyEditorSupport() {
            @Override
            public void setAsText(String itemId) {
                setValue((itemId != null && !itemId.isEmpty()) ? productService.getProductById(itemId) : null);
            }
        });

        binder.registerCustomEditor(UnitOfMeasure.class, "unit", new PropertyEditorSupport() {
            @Override
            public void setAsText(String unitId) {
                setValue((unitId != null && !unitId.isEmpty()) ? unitService.getUnitOfMeasureById(unitId) : null);
            }
        });

        binder.registerCustomEditor(Order.class, "order", new PropertyEditorSupport() {
            @Override
            public void setAsText(String orderId) {
                setValue((orderId != null && !orderId.isEmpty()) ? orderService.findOrderById(orderId) : null);
            }
        });
    }
}