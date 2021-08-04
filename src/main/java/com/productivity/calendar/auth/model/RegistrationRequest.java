package com.productivity.calendar.auth.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class RegistrationRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    private String name;

    @NotBlank
    private String last;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<Long> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Long> getRole() {
        return role;
    }

    public void setRole(Set<Long> role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
