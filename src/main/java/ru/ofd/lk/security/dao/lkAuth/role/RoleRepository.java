package ru.ofd.lk.security.dao.lkAuth.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ofd.lk.security.auth.ApplicationRoles;
import ru.ofd.lk.security.models.lkAuth.AuthRole;

import java.util.List;
import java.util.Optional;

@Repository
@SuppressWarnings("all")
public class RoleRepository {

    private final JdbcTemplate template;

    @Autowired
    public RoleRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<AuthRole> getRoles() {
        return template.query("select * from ROLES", (rs,i)-> new AuthRole(rs.getString("ID"), rs.getString("NAME")));
    }

    public Optional<AuthRole> getRole(ApplicationRoles role) {
        return template.query("select * from ROLES where NAME=?", new Object[]{role.toString()},
                (rs,i)-> new AuthRole(rs.getString("ID"), rs.getString("NAME"))).stream().findFirst();
    }

    public void createNew(AuthRole role) {
        template.update("insert into ROLES (ID, NAME) values ( random_uuid(), ? )", role.getRoleName());
    }
}
