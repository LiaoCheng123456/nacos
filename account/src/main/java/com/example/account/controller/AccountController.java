package com.example.account.controller;

import com.example.account.service.IAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AccountController {

    @Resource(name = "accountService")
    private IAccountService accountService;

    @GetMapping(value = "test")
    public String test() {
        return accountService.updateAccountService("sss");

    }
}
