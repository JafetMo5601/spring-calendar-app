package com.productivity.calendar.appointments.repository;

import com.productivity.calendar.appointments.model.Appointment;
import com.productivity.calendar.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{
    Appointment findByTitle(String title);
    List<Appointment> findByUserName(User userName);
    Boolean existsByTitle(String title);
}
