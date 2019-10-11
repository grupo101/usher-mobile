package com.usher.usher.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface MainMenuActivityPresenter {

    void checkSesion();

    void checkAccess(String username);

    void onSesion(boolean status);

    void offSesion(boolean status);

    void accessSuccessfull();

    void accessFailed() ;

    void showErrorPresenter(JSONException error);

    void setName(String name, String surname);
}
