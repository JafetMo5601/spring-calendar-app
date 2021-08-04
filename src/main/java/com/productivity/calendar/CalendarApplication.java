package com.productivity.calendar;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;


@SpringBootApplication
@EnableJpaAuditing
public class CalendarApplication {
    public static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class, args);
    }
}
