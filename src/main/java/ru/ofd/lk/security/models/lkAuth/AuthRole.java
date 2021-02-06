package ru.ofd.lk.security.models.lkAuth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ru.ofd.lk.security.models.ApplicationRole;
import ru.ofd.lk.security.models.ApplicationEntity;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRole extends ApplicationEntity implements ApplicationRole {

    public AuthRole(String id, String roleName){
        super(id);
        setRoleName(roleName);
    }

    String roleName;
}
