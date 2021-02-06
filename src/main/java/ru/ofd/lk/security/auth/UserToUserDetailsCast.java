package ru.ofd.lk.security.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.ofd.lk.security.models.ApplicationRole;
import ru.ofd.lk.security.models.ApplicationUser;

import java.util.List;
import java.util.stream.Collectors;

public final class UserToUserDetailsCast {

    protected static ApplicationUserDetails cast(ApplicationUser user) {
        return new ApplicationUserDetails(user.getId(), user.getUserName(), user.getPassword(), user.getEmail(), user.isActive(), castRoles(user.getRoles()));
    }

    private static List<GrantedAuthority> castRoles(List<? extends ApplicationRole> roles) {
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
