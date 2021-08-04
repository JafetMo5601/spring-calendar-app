package com.productivity.calendar.auth.services;

import com.productivity.calendar.auth.model.RegistrationRequest;
import com.productivity.calendar.auth.model.User;

public interface IRegistrationService {
    User registerNewUser(RegistrationRequest registrationRequest);
}
