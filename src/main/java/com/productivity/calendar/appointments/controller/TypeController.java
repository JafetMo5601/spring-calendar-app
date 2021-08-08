package com.productivity.calendar.appointments.controller;

import com.productivity.calendar.auth.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

@Profile("dev")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/types")
public class TypeController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @PostMapping("/create")
    public ResponseEntity<?> addNewAppointment() {
        EntityManager session = entityManagerFactory.createEntityManager();
        try {
            session.createNativeQuery("INSERT INTO public.types(id, color, name) VALUES (1, '#AD2121', 'TYPE_APPOINTMENT');" +
                    "INSERT INTO public.types(id, color, name) VALUES (2, '#1E90FF', 'TYPE_EVENT');" +
                    "INSERT INTO public.types(id, color, name) VALUES (3, '#E3BC08', 'TYPE_VACATION');" +
                    "INSERT INTO roles(id, name) VALUES (1, 'ROLE_USER');" +
                    "INSERT INTO roles(id, name) VALUES (2, 'ROLE_MODERATOR');" +
                    "INSERT INTO roles(id, name) VALUES (3, 'TYPE_ADMIN');")
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
        finally {
            if(session.isOpen()) session.close();
            return ResponseEntity.ok(new MessageResponse("Type created successfully!"));
        }
    }
}
