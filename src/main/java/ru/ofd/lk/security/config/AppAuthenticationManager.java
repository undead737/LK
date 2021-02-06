package ru.ofd.lk.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.ofd.lk.exceptions.AppDataNotFoundException;
import ru.ofd.lk.security.auth.jwt.JwtUserService;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

import java.util.stream.Collectors;

/**
 * Override to display custom exceptions
 */
@Configuration
public class AppAuthenticationManager implements AuthenticationManager {

    @Autowired
    private JwtUserService userService;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        AuthUser user = null;
        try {
            user = userService.getUserByName(username);
        } catch (AppDataNotFoundException e) {
            throw new BadCredentialsException("User not found");
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        if (!user.isActive()) {
            throw new DisabledException("Not active user");
        }
        return new UsernamePasswordAuthenticationToken(username, null, user.getRoles().stream().map(x -> new SimpleGrantedAuthority(x.getRoleName())).collect(Collectors.toList()));
    }
}
