package com.productivity.calendar.appointments.model;

import com.productivity.calendar.appointments.repository.TypeRepository;
import com.productivity.calendar.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.productivity.calendar.auth.model.User;
import javax.validation.constraints.NotNull;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "appointments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "title")
        })
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    private String location;

    private String notes;

    @NotNull
    private String start;

    private String ends;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appointment_type",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> type = new HashSet<>();

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "appointment_user",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> userName = new HashSet<>();

    public Appointment() {

    }

    public Appointment(String title, String location, String notes, String start_time, String end_time) {
        this.title = title;
        this.location = location;
        this.notes = notes;
        this.start = start_time;
        this.ends = end_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnds() {
        return ends;
    }

    public void setEnds(String ends) {
        this.ends = ends;
    }

    public Set<Type> getType() {
        return type;
    }

    public void setType(Set<Type> type) {
        this.type = type;
    }

    public Set<User> getUserName() {
        return userName;
    }

    public void setUserName(Set<User> userName) {
        this.userName = userName;
    }
}
