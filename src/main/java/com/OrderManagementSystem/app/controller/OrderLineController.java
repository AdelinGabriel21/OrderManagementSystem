package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.OrderLine;
import com.OrderManagementSystem.app.model.Product;
import com.OrderManagementSystem.app.model.UnitOfMeasure;
import com.OrderManagementSystem.app.service.OrderLineService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderLines")
public class OrderLineController {

    private final ProductService productService;
    private final UnitOfMeasureService unitService;
    private final OrderLineService service;

    public OrderLineController(ProductService serviceProduct, UnitOfMeasureService serviceUnit, OrderLineService service) {
        this.productService = serviceProduct;
        this.unitService = serviceUnit;
        this.service = service;
    }

    @GetMapping
    public String listOrderLines(Model model) {
        model.addAttribute("orderLines", service.getAllOrderLines());
        return "orderLine/index";
    }

    @GetMapping("/new")
    public String newOrderLineForm(Model model) {
        model.addAttribute("orderLine", new OrderLine());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("units", unitService.getAllUnitsOfMeasure());
        return "orderLine/form";
    }

    @PostMapping
    public String addOrderLine(@RequestParam double quantity,
                               @RequestParam String productID,
                               @RequestParam String unitID) {
        UnitOfMeasure unit = unitService.getUnitOfMeasureById(unitID);
        Product product = productService.getProductById(productID);
        OrderLine orderLine = new OrderLine(product, unit, quantity);
        service.saveOrderLine(orderLine);
        return "redirect:/orderLines";
    }

    @PostMapping("/{id}/delete")
    public String deleteOrderLine(@PathVariable String id) {
        service.deleteOrderLine(id);
        return "redirect:/orderLines";
    }


}
