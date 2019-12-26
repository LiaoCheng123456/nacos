package com.example.account.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;

@Repository(value = "accountImpl")
public class IAccountDaoImpl{
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    public void addAccount(HashMap map) {
        sqlSessionTemplate.insert("AccountMapper.addAccount", map);
    }
}
