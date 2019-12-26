package com.example.order.controller;

import com.example.order.service.IOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderController {

    @Resource(name = "orderService")
    private IOrderService orderService;

    @GetMapping(value = "test")
    public String test() {
        return orderService.createOrder("sss");

    }
}
