package ru.ofd.lk.security.auth;

import ru.ofd.lk.exceptions.AppDataNotFoundException;
import ru.ofd.lk.security.dao.RoleDao;
import ru.ofd.lk.security.models.ApplicationRole;

import java.util.List;

@SuppressWarnings("unused")
public abstract class RoleService<T extends ApplicationRole> {

    protected final RoleDao<T> dao;

    public RoleService(RoleDao<T> dao) {
        this.dao = dao;
    }

    public T getRole(ApplicationRoles role) throws AppDataNotFoundException {
        return dao.getRole(role).orElseThrow(AppDataNotFoundException::new);
    }

    public List<T> getRoles() {return dao.getRoles();}

    public abstract void createNew(T role);
}
