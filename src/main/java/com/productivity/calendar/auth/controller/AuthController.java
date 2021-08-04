package com.productivity.calendar.auth.controller;

import com.productivity.calendar.auth.services.IAuthenticationService;
import com.productivity.calendar.auth.services.IRegistrationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.productivity.calendar.auth.repository.RoleRepository;
import com.productivity.calendar.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.productivity.calendar.auth.services.MyUserDetails;
import org.springframework.security.core.Authentication;
import com.productivity.calendar.auth.util.JwtUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.productivity.calendar.auth.model.*;
import java.util.stream.Collectors;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private IRegistrationService registrationService;

    @Autowired
    UserRepository userRepository;


    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        return ResponseEntity.ok(new AuthenticationResponse(
                authenticationService.getJWTToken(authentication),
                authenticationService.getUser(authentication).getId(),
                authenticationService.getUser(authentication).getName(),
                authenticationService.getUser(authentication).getLast(),
                authenticationService.getUser(authentication).getUsername(),
                authenticationService.getUser(authentication).getEmail(),
                authenticationService.getRoles(authenticationService.getUser(authentication))));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }
        userRepository.save(registrationService.registerNewUser(signUpRequest));
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}