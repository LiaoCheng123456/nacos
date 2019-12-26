package com.example.account.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.account.dao.IAccountDaoImpl;

import javax.annotation.Resource;
import java.util.HashMap;

@Service(group = "accountService", retries = 0, timeout = 10000)
@org.springframework.stereotype.Service(value = "accountService")
public class AccountService implements IAccountService{

    @Resource(name = "accountImpl")
    private IAccountDaoImpl accountDao;

    @Override
    public String updateAccountService(String account) {
        System.out.println("accountService服务运行");
        accountDao.addAccount(new HashMap());
        return "accountService";
    }
}
