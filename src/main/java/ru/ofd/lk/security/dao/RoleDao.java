package ru.ofd.lk.security.dao;

import org.springframework.stereotype.Component;
import ru.ofd.lk.dao.ApplicationDao;
import ru.ofd.lk.security.auth.ApplicationRoles;
import ru.ofd.lk.security.models.ApplicationRole;
import ru.ofd.lk.security.models.lkAuth.AuthRole;

import java.util.List;
import java.util.Optional;

@Component
public interface RoleDao<T extends ApplicationRole> extends ApplicationDao {
    List<T> getRoles();
    Optional<T> getRole(ApplicationRoles role);
    void createNew(T role);
}
