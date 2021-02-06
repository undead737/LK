package ru.ofd.lk.security.models;

import java.util.List;

public interface ApplicationUser {
    String getId();
    String getUserName();
    String getPassword();
    String getEmail();
    boolean isActive();
    List<? extends ApplicationRole> getRoles();
}
