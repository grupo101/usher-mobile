package com.usher.usher.interfaces;

import com.usher.usher.views.RegisterUserActivity;

public interface RegisterUserActivityInteractor {

    void doRegisterValidations(String name, String surname, String username, String password, String access, RegisterUserActivity view);
}
