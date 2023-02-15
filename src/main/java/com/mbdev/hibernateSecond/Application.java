package com.mbdev.hibernateSecond;

import com.mbdev.hibernateSecond.dao.AccountDAO;
import com.mbdev.hibernateSecond.entity.Account;
import com.mbdev.hibernateSecond.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, JpaRepositoriesAutoConfiguration.class})
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private AccountDAO accountDAO;

    public void run(String... args) throws Exception {
        Account account = new Account("Lori", 15);
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(10));
        bills.add(new Bill(11));
        bills.add(new Bill(12));
        bills.add(new Bill(103));
        bills.add(new Bill(105));
        account.setBills(bills);
        Account savedAccount = accountDAO.save(account);

        Account accountFromDatabase = accountDAO.findById(savedAccount.getAccountId());
        System.out.println(accountFromDatabase);
        System.out.println(accountFromDatabase.getBills());
    }
}
