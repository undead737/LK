package ru.ofd.lk.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.ofd.lk.exceptions.AppDataNotFoundException;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

   private final UserService<AuthUser> userService;

   @Autowired
    public ApplicationUserDetailsService(UserService<AuthUser> userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) {
        AuthUser user;
        try {
            user = userService.getUserByName(userName);
        } catch (AppDataNotFoundException e) {
            throw new UsernameNotFoundException("");
        }
        return UserToUserDetailsCast.cast(user);
    }
}
