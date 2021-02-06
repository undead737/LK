package ru.ofd.lk.security.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ofd.lk.security.models.lkAuth.AuthUser;

@Service
public class AuthPasswordCrypt implements ApplicationCrypt<AuthUser>{
    @Override
    public String crypt(String value) {
        return new BCryptPasswordEncoder().encode(value);
    }
}
