package com.vti.service.impl;

import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.repository.IAccountRepository;
import com.vti.repository.IDepartmentRepository;
import com.vti.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        List<Account> accountss = accountRepository.findAll();
        return accountss;
    }

    @Override
    public Account findById(Integer id) {
        Account account = accountRepository.findById(id).orElse(null);
        return account;
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void create(Account account) {
        accountRepository.save(account);

    }

    @Override
    public void update(Account account, Integer id) {
        Account accountUpdate = accountRepository.findById(id).orElse(null);
        if (Objects.isNull(accountUpdate)) {
            throw new RuntimeException("ID not found!");
        } else {
            accountUpdate.setUsername(account.getUsername());
            accountUpdate.setFullName(account.getFullName());
            accountUpdate.setEmail(account.getEmail());
            accountUpdate.setDep(account.getDep());

            accountRepository.save(accountUpdate);
        }
    }

}
