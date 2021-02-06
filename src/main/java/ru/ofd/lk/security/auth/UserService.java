package ru.ofd.lk.security.auth;

import ru.ofd.lk.security.dao.UserDao;
import ru.ofd.lk.exceptions.AppDataNotFoundException;
import ru.ofd.lk.security.models.ApplicationUser;

import java.util.List;

@SuppressWarnings("unused")
public abstract class UserService<T extends ApplicationUser> {

    protected final UserDao<T> dao;

    public UserService(UserDao<T> dao) {
        this.dao = dao;
    }

    public List<T> getUsers(){
        return dao.getUsers();
    }

    public T getUserById(String id) throws AppDataNotFoundException {
        return dao.getUserById(id).orElseThrow(AppDataNotFoundException::new);
    }

    public T getUserByName(String name) throws AppDataNotFoundException {
        return dao.getUserByName(name).orElseThrow(AppDataNotFoundException::new);
    }

    public abstract void register(T user) throws Exception;
}
