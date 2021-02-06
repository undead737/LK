package ru.ofd.lk.security.auth.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ofd.lk.exceptions.AppDataNotFoundException;
import ru.ofd.lk.security.auth.ApplicationRoles;
import ru.ofd.lk.security.auth.RoleService;
import ru.ofd.lk.security.auth.UserService;
import ru.ofd.lk.security.crypt.ApplicationCrypt;
import ru.ofd.lk.security.dao.UserDao;
import ru.ofd.lk.security.models.lkAuth.AuthRole;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

import java.util.Collections;

@Service
public class JwtUserService extends UserService<AuthUser> {

    private final ApplicationCrypt<AuthUser> crypt;
    private final RoleService<AuthRole> roleService;

    @Autowired
    public JwtUserService(@Qualifier("authUserDao") UserDao<AuthUser> dao, ApplicationCrypt<AuthUser> crypt, RoleService<AuthRole> roleService) {
        super(dao);
        this.crypt = crypt;
        this.roleService = roleService;
    }

    @Override
    @Transactional
    public void register(AuthUser user) throws Exception {
        if ((user.getUserName() == null || user.getPassword() == null || user.getEmail() == null) ||
                (user.getUserName().equals("") || user.getPassword().equals("") || user.getEmail().equals("")))
            throw new AppDataNotFoundException("userName + pass + email is required!");
        user.setActive(true);
        user.setRoles(Collections.singletonList(roleService.getRole(ApplicationRoles.USER)));
        user.setPassword(crypt.crypt(user.getPassword()));
        try {
            super.dao.register(user);
        } catch (DuplicateKeyException ex){
            throw new Exception("user with this login/email already exists!");
        } catch (Exception ex){
            throw new Exception("unchecked: " + ex.getMessage());
        }
    }
}
