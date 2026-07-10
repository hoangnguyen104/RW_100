package com.vti.service;

import com.vti.dto.AccountDTO;
import com.vti.entity.Account;
import com.vti.entity.Department;
import com.vti.form.AccountCreateOrUpdateForm;

import java.util.List;

public interface    IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    void deleteById(Integer id);

    void create(AccountCreateOrUpdateForm  account);

    void update(AccountCreateOrUpdateForm account, Integer id);
}
