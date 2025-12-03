package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.OrderLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.OrderLineService;
import com.OrderManagementSystem.app.service.OrderService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

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
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("units", unitService.getAllUnitsOfMeasure());
        model.addAttribute("orders", orderService.findAllOrders());
        return "orderLine/form";
    }

    @GetMapping("/new")
    public String newOrderLineForm(Model model) {
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("units", unitService.getAllUnitsOfMeasure());
        model.addAttribute("orders", orderService.findAllOrders());

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
                               Model model,
                               @RequestParam String productID,
                               @RequestParam String unitID,
                               @RequestParam String orderID) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("products", productService.getAllProducts());
            model.addAttribute("units", unitService.getAllUnitsOfMeasure());
            model.addAttribute("orders", orderService.findAllOrders());
            return "orderLine/form";
        }

        UnitOfMeasure unit = unitService.getUnitOfMeasureById(unitID);
        Product product = productService.getProductById(productID);

        com.OrderManagementSystem.app.model.Order order = orderService.findOrderById(orderID);
        orderLine.setOrder(order);

        orderLine.setItem(product);
        orderLine.setUnit(unit);

        service.saveOrderLine(orderLine);
        return "redirect:/orderLines";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrderLine(@PathVariable String id) {
        service.deleteOrderLine(id);
        return "redirect:/orderLines";
    }
}