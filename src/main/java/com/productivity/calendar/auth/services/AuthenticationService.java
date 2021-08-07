package com.productivity.calendar.auth.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import com.productivity.calendar.auth.util.JwtUtil;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;


@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public Authentication getAuthentication(String username, String password) {
        return null;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public String getJWTToken(Authentication authentication) {
        return jwtUtil.generateJwtToken(authentication);
    }

    @Override
    public MyUserDetails getUser(Authentication authentication) {
        return (MyUserDetails) authentication.getPrincipal();
    }

    @Override
    public List<String> getRoles(MyUserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
    }
}
