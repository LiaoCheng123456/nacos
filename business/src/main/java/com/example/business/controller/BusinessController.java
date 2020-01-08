package com.example.business.controller;

import com.alibaba.dubbo.config.annotation.Reference;
//import com.example.account.service.IAccountService;
import com.example.business.service.BusinessService;
//import com.example.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController(value = "test")
public class BusinessController {

    @Resource(name = "businessService")
    private BusinessService businessService;


//    @GetMapping(value = "test")
//    public String test() {
//        return businessService.testService("");
//    }

    @GetMapping(value = "test1")
    public String test1() {
        businessService.redissonTest();
        return null;
    }

    @GetMapping(value = "latch")
    public String latch() throws InterruptedException {
        return businessService.latch();
    }

    @GetMapping(value = "countdown")
    public String countdown() {
        return businessService.countdown();
    }

    /**
     * 测试使用注解完成缓存
     * @return
     */
    @GetMapping(value = "cache/{pid}")
    public String cache(@PathVariable("pid") String pid) {
        return businessService.cache(pid);
    }



}
