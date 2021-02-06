package ru.ofd.lk.security.dao;

import org.springframework.stereotype.Component;
import ru.ofd.lk.dao.ApplicationDao;
import ru.ofd.lk.security.models.ApplicationUser;

import java.util.List;
import java.util.Optional;

@Component
public interface UserDao<T extends ApplicationUser> extends ApplicationDao {
    List<T> getUsers();
    Optional<T> getUserById(String id);
    Optional<T> getUserByName(String name);
    void register(T user);
}
