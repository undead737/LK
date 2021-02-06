package ru.ofd.lk.security.dao.lkAuth.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ofd.lk.security.auth.ApplicationRoles;
import ru.ofd.lk.security.dao.RoleDao;
import ru.ofd.lk.security.models.lkAuth.AuthRole;

import java.util.List;
import java.util.Optional;

@Component
public class AuthRoleDao implements RoleDao<AuthRole> {

    private final RoleRepository repository;
    private final static String name = "AuthRoleDao";

    @Autowired
    public AuthRoleDao(RoleRepository repository) {
        this.repository = repository;
    }

    public List<AuthRole> getRoles() {
        return repository.getRoles();
    }

    @Override
    public Optional<AuthRole> getRole(ApplicationRoles role) {
        return repository.getRole(role);
    }

    @Override
    public void createNew(AuthRole role) {
        repository.createNew(role);
    }

    @Override
    public String getName() {
        return name;
    }
}
