package com.example.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.account.service.IAccountService;
import com.example.business.service.BusinessService;
import com.example.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "test")
public class BusinessController {

    @Resource(name = "businessService")
    private BusinessService businessService;


    @GetMapping(value = "test")
    public String test() {
        return businessService.testService("");
    }
}
