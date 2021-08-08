package com.productivity.calendar.appointments.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class AppointmentCreationRequest {

    @NotBlank
    @NotNull
    private String title;

    private String location;

    private String notes;

    @NotBlank
    @NotNull
    private String start;

    private String ends;

    @NotBlank
    @NotNull
    private String type;

    @NotBlank
    @NotNull
    private String user;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
