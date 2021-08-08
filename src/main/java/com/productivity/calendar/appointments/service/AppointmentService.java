package com.productivity.calendar.appointments.service;

import com.productivity.calendar.appointments.model.AppointmentCreationRequest;
import com.productivity.calendar.appointments.repository.AppointmentRepository;
import com.productivity.calendar.appointments.repository.TypeRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.productivity.calendar.appointments.model.Appointment;
import com.productivity.calendar.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.productivity.calendar.appointments.model.Type;
import com.productivity.calendar.auth.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Page<Appointment> retrieveAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    @Override
    public List<Appointment> retrieveAppointmentsByUserName() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username);
        return appointmentRepository.findByUserName(user);
    }

    @Override
    public Appointment retrieveAppointmentById(Long id) {
        if (appointmentRepository.existsById(id)) {
            return appointmentRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public Appointment createNewAppointment(AppointmentCreationRequest newRequest) {
        if(!appointmentRepository.existsByTitle(newRequest.getTitle())){
            Appointment newAppointment = new Appointment(
                    newRequest.getTitle(),
                    newRequest.getLocation(),
                    newRequest.getNotes(),
                    newRequest.getStart(),
                    newRequest.getEnds()
            );
            newAppointment.setUserName(retrieveUser(newRequest.getUser()));
            newAppointment.setType(retrieveType(Long.parseLong(newRequest.getType())));
            return newAppointment;
        }
        return null;
    }

    @Override
    public Appointment patchAppointment(Long id, AppointmentCreationRequest appointment) {
        if(appointmentRepository.existsById(id)) {
            Appointment appointmentToUpdated = appointmentRepository.findById(id).get();
            appointmentToUpdated.setType(retrieveType(Long.parseLong(appointment.getType())));
            appointmentToUpdated.setUserName(retrieveUser(appointment.getUser()));
            appointmentToUpdated.setTitle(appointment.getTitle());
            appointmentToUpdated.setNotes(appointment.getNotes());
            appointmentToUpdated.setLocation(appointment.getLocation());
            appointmentToUpdated.setStart(appointment.getStart());
            appointmentToUpdated.setEnds(appointment.getEnds());
            return appointmentToUpdated;
        }
        return null;
    }

    @Override
    public Boolean deleteAppointment(Long id) {
        if(appointmentRepository.existsById(id)) {
            appointmentRepository.delete(appointmentRepository.findById(id).get());
            return true;
        }
        return false;
    }

    private Set<Type> retrieveType(Long id) {
        Set<Type> setType = new HashSet<>();
        if(!(id > 3 || id < 1)) {
            setType.add(typeRepository.findById(id).get());
        } else {
            setType.add(typeRepository.findById(1L).get());
        }
        return setType;
    }

    private Set<User> retrieveUser(String user) {
        Set<User> setUser = new HashSet<>();
        setUser.add(userRepository.findByUsername(user));
        return setUser;
    }
}
