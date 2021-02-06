package ru.ofd.lk.security.crypt;

import ru.ofd.lk.security.models.ApplicationUser;

public interface ApplicationCrypt<T extends ApplicationUser> {
    String crypt(String value);
}
