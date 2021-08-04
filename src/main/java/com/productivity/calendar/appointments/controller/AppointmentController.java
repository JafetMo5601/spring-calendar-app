package com.productivity.calendar.appointments.controller;

import com.productivity.calendar.appointments.repository.AppointmentRepository;
import com.productivity.calendar.appointments.service.IAppointmentService;
import com.productivity.calendar.appointments.repository.TypeRepository;
import com.productivity.calendar.appointments.model.CreationRequest;
import com.productivity.calendar.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import com.productivity.calendar.appointments.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import com.productivity.calendar.auth.model.MessageResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/entire")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<Page<Appointment>> getAllAppointments(Pageable pageable) {
        return new ResponseEntity(appointmentService.retrieveAllAppointments(pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Appointment>> getAllAppointmentsByUsername() {
        return new ResponseEntity(appointmentService.retrieveAppointmentsByUserName(), HttpStatus.OK);
    }

    @GetMapping("/retrieve/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        if (appointmentService.retrieveAppointmentById(id) != null) {
            return new ResponseEntity(appointmentService.retrieveAppointmentById(id), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<String>("Appointment not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> addNewAppointment(@Valid @RequestBody CreationRequest appointCreation) {
        if(appointmentService.createNewAppointment(appointCreation) != null) {
            appointmentRepository.save(appointmentService.createNewAppointment(appointCreation));
            return ResponseEntity.ok(new MessageResponse("Task created successfully!"));
        } else {
            return ResponseEntity.badRequest().body("Title already exist.");
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN') or hasRole('MODERATOR')")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @Valid @RequestBody CreationRequest requestAppointment) {
        if (appointmentService.patchAppointment(id, requestAppointment) != null) {
            appointmentRepository.save(appointmentService.patchAppointment(id, requestAppointment));
            return ResponseEntity.ok(new MessageResponse("Appointment updated."));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeAppointment(@PathVariable Long id) {
        if (appointmentService.deleteAppointment(id)) {
            return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
