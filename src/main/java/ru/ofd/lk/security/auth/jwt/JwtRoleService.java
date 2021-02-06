package ru.ofd.lk.security.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ofd.lk.security.auth.RoleService;
import ru.ofd.lk.security.dao.RoleDao;
import ru.ofd.lk.security.models.lkAuth.AuthRole;

@Service
public class JwtRoleService extends RoleService<AuthRole> {

    @Autowired
    public JwtRoleService(@Qualifier("authRoleDao") RoleDao<AuthRole> dao) {
        super(dao);
    }

    @Override
    @Transactional
    public void createNew(AuthRole role) {
        super.dao.createNew(role);
    }
}
