package ru.ofd.lk.dao.balance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ofd.lk.dao.BalanceDao;

@Component
public class ImplBalanceDao implements BalanceDao {

    private final BalanceRepository repository;
    private final static String name = "ImplBalanceDao";

    @Autowired
    public ImplBalanceDao(BalanceRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getBalanceByUserId(String userId) {
        return repository.getBalanceByUserId(userId);
    }

    @Override
    public void editBalance(String userId, int balance) {
        this.repository.editBalance(userId, balance);
    }

    @Override
    public String getName() {
        return name;
    }
}
