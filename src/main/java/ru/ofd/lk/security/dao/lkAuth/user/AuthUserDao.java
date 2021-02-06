package ru.ofd.lk.security.dao.lkAuth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ofd.lk.security.dao.UserDao;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

import java.util.List;
import java.util.Optional;

@Component
public class AuthUserDao implements UserDao<AuthUser> {

    private final UserRepository repository;
    private final static String name = "AuthUserDao";

    @Autowired
    public AuthUserDao(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<AuthUser> getUsers() {
        return repository.getUsers();
    }

    @Override
    public Optional<AuthUser> getUserById(String id) {
        return repository.getUserById(id);
    }

    @Override
    public void register(AuthUser user) {
        repository.register(user);
    }

    @Override
    public Optional<AuthUser> getUserByName(String name){
        return repository.getUserByName(name);
    }

    @Override
    public String getName() {
        return name;
    }
}
