package ru.ofd.lk.security.models.lkAuth;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.ofd.lk.security.models.ApplicationUser;
import ru.ofd.lk.security.models.ApplicationEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthUser extends ApplicationEntity implements ApplicationUser {

    public AuthUser(String id, String userName, String password, String email, String firstName, String lastName, String patronymic, String birthDay, boolean isActive, List<AuthRole> roles) {
        super(id);
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
        this.isActive = isActive;
        this.roles = roles;
    }

    String userName;
    String password;
    String email;
    String firstName;
    String lastName;
    String patronymic;
    String birthDay;
    boolean isActive;
    List<AuthRole> roles;
}
