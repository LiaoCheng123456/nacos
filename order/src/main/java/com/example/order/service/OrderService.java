package com.example.order.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.order.dao.IOrderDaoImpl;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;

@Service(group = "orderService", retries = 0, timeout = 10000)
@org.springframework.stereotype.Service(value = "orderService")
public class OrderService implements IOrderService{
    @Resource(name = "orderImpl")
    private IOrderDaoImpl orderDao;
    @Override
    @Transactional
    public String createOrder(String order) {
        System.out.println("orderService服务运行");
        orderDao.addOrder(new HashMap());
        return "orderService";
    }
}
