package com.goldwarehouse.mapper;

import com.goldwarehouse.entity.Account;

import java.util.List;

public interface AccountExtendsMapper extends AccountMapper {
    List<Account> getAllAccounts();
}