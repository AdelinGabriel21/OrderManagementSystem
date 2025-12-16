package com.OrderManagementSystem.app.controller;

import com.OrderManagementSystem.app.model.*;
import com.OrderManagementSystem.app.service.OrderLineService;
import com.OrderManagementSystem.app.service.OrderService;
import com.OrderManagementSystem.app.service.ProductService;
import com.OrderManagementSystem.app.service.UnitOfMeasureService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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
    public String showOrderLines(Model model,
                                 @RequestParam(required = false) String itemName,
                                 @RequestParam(required = false) String unitName,
                                 @RequestParam(required = false) String orderName,
                                 @RequestParam(defaultValue = "none") String sortField1,
                                 @RequestParam(defaultValue = "asc") String sortDir1,
                                 @RequestParam(defaultValue = "none") String sortField2,
                                 @RequestParam(defaultValue = "asc") String sortDir2) {

        model.addAttribute("orderLines", service.searchOrderLines(itemName, unitName, orderName, sortField1, sortDir1, sortField2, sortDir2));

        model.addAttribute("filterItemName", itemName);
        model.addAttribute("filterUnitName", unitName);
        model.addAttribute("filterOrderName", orderName);

        model.addAttribute("sortField1", sortField1);
        model.addAttribute("sortDir1", sortDir1);
        model.addAttribute("sortField2", sortField2);
        model.addAttribute("sortDir2", sortDir2);

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
        service.deleteOrderLine(id);

        return "redirect:/orderLines";
    }

    private void populateDependencies(Model model) {
        model.addAttribute("products", productService.searchProducts(null, null, null, "name", "asc", "name", "asc"));
        model.addAttribute("units", unitService.searchUnits(null, null, "name","asc", "name","asc"));
        model.addAttribute("orders", orderService.searchOrders(null, null, null, "name","asc", "name", "asc"));
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