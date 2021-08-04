package com.productivity.calendar.appointments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.productivity.calendar.appointments.model.EType;
import com.productivity.calendar.appointments.model.Type;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {
    Boolean existsById(EType name);
    Type findByName(String name);
    Optional<Type> findById(Long id);
}