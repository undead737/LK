package ru.ofd.lk.dao;

public interface BalanceDao extends ApplicationDao {
    int getBalanceByUserId(String userId);
    void editBalance(String userId, int balance);
}
