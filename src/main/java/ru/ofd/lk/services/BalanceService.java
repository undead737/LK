package ru.ofd.lk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ofd.lk.dao.BalanceDao;
import ru.ofd.lk.dao.balance.ImplBalanceDao;

@Service
public class BalanceService {

    private final BalanceDao dao;

    @Autowired
    public BalanceService(ImplBalanceDao dao){
        this.dao = dao;
    }

    public int getBalanceByUserId(String userId) {
        return dao.getBalanceByUserId(userId);
    }

    public void editBalance(String userId, int balance){
        dao.editBalance(userId, balance);
    }

}
