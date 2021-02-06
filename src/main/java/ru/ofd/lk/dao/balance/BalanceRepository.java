package ru.ofd.lk.dao.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BalanceRepository {

    private final JdbcTemplate template;

    @Autowired
    public BalanceRepository(JdbcTemplate template) {
        this.template = template;
    }

    @SuppressWarnings("all")
    public int getBalanceByUserId(String userId){
        return template.queryForObject("select BALANCE from BALANCE where USER_ID=?", Integer.class, userId);
    }

    public void editBalance(String userId, int balance){
        template.update("update BALANCE set BALANCE=? where USER_ID=?", balance, userId);
    }
}
