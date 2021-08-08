package com.productivity.calendar.appointments.service;

import com.productivity.calendar.appointments.model.AppointmentCreationRequest;
import com.productivity.calendar.appointments.model.Appointment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

public interface IAppointmentService {
    Page<Appointment> retrieveAllAppointments(Pageable pageable);
    List<Appointment> retrieveAppointmentsByUserName();
    Appointment retrieveAppointmentById(Long id);
    Appointment createNewAppointment(AppointmentCreationRequest newRequest);
    Appointment patchAppointment(Long id, AppointmentCreationRequest appointment);
    Boolean deleteAppointment(Long id);
}
