package com.productivity.calendar.auth.services;

import com.productivity.calendar.auth.model.User;
import com.productivity.calendar.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.existsByUsername(username)) {
            User user = userRepository.findByUsername(username);
            return MyUserDetails.build(user);
        } else {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
    }
}
