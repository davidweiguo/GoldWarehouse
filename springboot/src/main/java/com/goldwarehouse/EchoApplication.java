package com.goldwarehouse;

import com.goldwarehouse.entity.Account;
import com.goldwarehouse.mapper.AccountExtendsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author guo_d
 * @date 2018/10/15
 */
@Configuration
@SpringBootApplication
public class EchoApplication {

    @Autowired
    private AccountExtendsMapper accountExtendsMapper;

    @PostConstruct
    public void init() {
        List<Account> accounts = accountExtendsMapper.getAllAccounts();
        for (Account account : accounts) {
            System.out.println(account.getUserId());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }
}
