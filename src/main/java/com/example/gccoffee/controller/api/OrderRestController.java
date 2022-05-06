package com.example.gccoffee.controller.api;

import com.example.gccoffee.controller.CreateOrderRequest;
import com.example.gccoffee.model.Email;
import com.example.gccoffee.model.Order;
import com.example.gccoffee.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public Order createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createOrder(
                new Email(createOrderRequest.getEmail()),
                createOrderRequest.getAddress(),
                createOrderRequest.getPostcode(),
                createOrderRequest.getOrderItems()
        );
    }

}
