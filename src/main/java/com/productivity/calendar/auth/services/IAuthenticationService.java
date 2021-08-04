package com.productivity.calendar.auth.services;

import org.springframework.security.core.Authentication;

import java.util.List;

public interface IAuthenticationService {
    Authentication getAuthentication(String username, String password);
    void setAuthentication(Authentication authentication);
    String getJWTToken(Authentication authentication);
    MyUserDetails getUser(Authentication authentication);
    List<String> getRoles(MyUserDetails userDetails);
}
