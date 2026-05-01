package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/account")
    public Account create(@RequestBody Account acc) {
        return service.createAccount(acc);
    }

  
    @GetMapping("/account/{id}")
    public Account get(@PathVariable Long id) {
        return service.getAccount(id);
    }

   
    @PostMapping("/transfer")
    public String transfer(@RequestParam Long from,
                           @RequestParam Long to,
                           @RequestParam double amount) {
        service.transfer(from, to, amount);
        return "Transfer Successful";
    }
}