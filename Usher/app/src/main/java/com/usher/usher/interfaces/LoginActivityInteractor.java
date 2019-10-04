package com.usher.usher.interfaces;

import com.usher.usher.views.LoginActivity;

public interface LoginActivityInteractor {

    void doLoginValidations(String username, String password);

    void volleyNoEntiendoInteractor(String username, String password, LoginActivity loginActivity);

}
