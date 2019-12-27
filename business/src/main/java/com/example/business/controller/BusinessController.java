package com.example.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.account.service.IAccountService;
import com.example.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "test")
public class BusinessController {

    @Reference(group = "accountService")
    private IAccountService accountService;

    @Reference(group = "orderService")
    private IOrderService orderService;


    @GetMapping(value = "test")
    @GlobalTransactional(timeoutMills = 300000, name = "order-gts-fescar-example")
    public String test() {

        System.out.println(accountService.updateAccountService("test"));
        System.out.println(orderService.createOrder("test"));
        int i = 100 / 0;
        return null;
    }
}
