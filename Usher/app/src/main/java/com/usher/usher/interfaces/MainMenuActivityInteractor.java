package com.usher.usher.interfaces;

import com.usher.usher.views.MainMenuActivity;

public interface MainMenuActivityInteractor {

    void checkSesionStatus(String username, MainMenuActivity mainMenuActivity);

    void doAccessValidation(String username, MainMenuActivity mainMenuActivity);

}
