package com.example.business.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.account.service.IAccountService;
import com.example.order.service.IOrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service(value = "businessService")
public class BusinessService {

    @Reference(group = "accountService")
    private IAccountService accountService;

    @Reference(group = "orderService")
    private IOrderService orderService;

    @GlobalTransactional(timeoutMills = 300000, name = "dubbo-gts-seata-example")
    public String testService(String service) {
        accountService.updateAccountService("test");
        orderService.createOrder("test");
        throw new RuntimeException("测试异常");
//        return null;
    }
}
