package com.productivity.calendar.auth.repository;

import com.productivity.calendar.auth.model.ERole;
import com.productivity.calendar.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
