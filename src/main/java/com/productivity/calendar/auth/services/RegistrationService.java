package com.productivity.calendar.auth.services;

import com.productivity.calendar.auth.model.RegistrationRequest;
import com.productivity.calendar.auth.model.Role;
import com.productivity.calendar.auth.model.User;
import com.productivity.calendar.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService implements IRegistrationService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public User registerNewUser(RegistrationRequest registrationRequest) {
        User user = new User(
                registrationRequest.getUsername(),
                registrationRequest.getName(),
                registrationRequest.getLast(),
                registrationRequest.getEmail(),
                encoder.encode(registrationRequest.getPassword()));

        user.setRoles(retrieveRoles(registrationRequest.getRole()));
        return user;
    }

    private Set<Role> retrieveRoles(Set<Long> requestedRoles) {
        Set<Role> setRole = new HashSet<>();

        if (requestedRoles == null) {
            setRole.add(roleRepository.findById(1L).get());
        } else {
            fillRoles(setRole, requestedRoles);
        }
        return setRole;
    }

    private void fillRoles(Set<Role> setRole, Set<Long> requestedRoles) {
        requestedRoles.forEach(role ->{
            if(!(role > 3 || role < 1)) {
                setRole.add(roleRepository.findById(role).get());
            } else {
                setRole.add(roleRepository.findById(1L).get());
            }
        });
    }
}
