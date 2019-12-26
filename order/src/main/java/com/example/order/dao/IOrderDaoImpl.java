package com.example.order.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

@Repository(value = "orderImpl")
public class IOrderDaoImpl{
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public void addOrder(HashMap map) {
        sqlSessionTemplate.insert("OrderMapper.addOrder", map);
    }
}
