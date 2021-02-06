package ru.ofd.lk.security.dao.lkAuth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.ofd.lk.security.models.lkAuth.AuthRole;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@SuppressWarnings("all")
public class UserRepository {

    private final JdbcTemplate template;

    @Autowired
    public UserRepository(JdbcTemplate template) {
        this.template = template;
    }

    public List<AuthUser> getUsers() {
        return template.query("select * from USERS", (rs, i) -> new AuthUser(
                rs.getString("ID"),
                rs.getString("USER_NAME"),
                rs.getString("PASSWORD"),
                rs.getString("EMAIL"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("PATRONYMIC"),
                rs.getString("BIRTH_DAY"),
                rs.getBoolean("IS_ACTIVE"),
                template.query("select * from USERS_ROLES WHERE USER_ID=?", new Object[]{rs.getString("ID")},
                        (rs1, i1) -> new AuthRole(rs1.getString("ID"), rs1.getString("NAME")))));
    }

    public Optional<AuthUser> getUserById(String id) {
        return template.query("select * from USERS where ID = ?", new Object[]{id}, (rs, i) -> new AuthUser(
                rs.getString("ID"),
                rs.getString("USER_NAME"),
                rs.getString("PASSWORD"),
                rs.getString("EMAIL"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("PATRONYMIC"),
                rs.getString("BIRTH_DAY"),
                rs.getBoolean("IS_ACTIVE"),
                template.query("select * from ROLES where ID in (select ROLE_ID from USERS_ROLES WHERE USER_ID=?)", new Object[]{rs.getString("ID")},
                        (rs1, i1) -> new AuthRole(rs1.getString("ID"), rs1.getString("NAME"))))).stream().findFirst();
    }

    public Optional<AuthUser> getUserByName(String name) {
        Optional<AuthUser> temp = template.query("select * from USERS where USER_NAME = ?", new Object[]{name}, (rs, i) -> new AuthUser(
                rs.getString("ID"),
                rs.getString("USER_NAME"),
                rs.getString("PASSWORD"),
                rs.getString("EMAIL"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("PATRONYMIC"),
                rs.getString("BIRTH_DAY"),
                rs.getBoolean("IS_ACTIVE"), null
                )).stream().findFirst();
        temp.get().setRoles(template.query("select * from ROLES where ID in (select ROLE_ID from USERS_ROLES WHERE USER_ID=?)", new Object[]{temp.get().getId()},
                (rs1, i1) -> new AuthRole(rs1.getString("ID"), rs1.getString("NAME"))));
        return temp;
    }

    public void register(AuthUser user) {
        String uuid = UUID.randomUUID().toString();
        template.update("insert into USERS (id, user_name, password, email, first_name, last_name, patronymic, birth_day, is_active) " +
                        "values ( ?, ?, ?, ?, ?, ?, ?, ?, ?)", uuid, user.getUserName(), user.getPassword(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getPatronymic(), user.getBirthDay(), user.isActive());
        template.update("insert into BALANCE (USER_ID) VALUES ( ? )", uuid);
        template.batchUpdate("insert into USERS_ROLES (USER_ID, ROLE_ID) values ( ?, ? )", user.getRoles().stream().map(x->new Object[]{uuid, x.getId()}).collect(Collectors.toList()));
    }
}
