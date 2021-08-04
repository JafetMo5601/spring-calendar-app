package com.productivity.calendar.appointments.model;

import javax.validation.constraints.NotNull;
import javax.persistence.*;


@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String color;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EType name;

    public Type() {
    }

    public Type(EType name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public EType getName() {
        return name;
    }

    public void setName(EType name) {
        this.name = name;
    }
}
