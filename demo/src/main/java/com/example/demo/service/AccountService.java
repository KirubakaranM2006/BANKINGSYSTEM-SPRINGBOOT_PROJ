package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository repo;

    public Account createAccount(Account acc) {
        if (acc.getBalance() < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }
        return repo.save(acc);
    }

    public Account getAccount(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void transfer(Long fromId, Long toId, double amount) {
        Account from = getAccount(fromId);
        Account to = getAccount(toId);

        if (from.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        repo.save(from);
        repo.save(to);
    }
}