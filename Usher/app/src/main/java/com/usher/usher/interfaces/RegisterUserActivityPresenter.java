package com.usher.usher.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface RegisterUserActivityPresenter {
    
    void doRegister(String name, String surname, String username, String password, String access);

    void onRegisterSuccessfull(JSONObject jsonResponse);

    void onRegisterFailed(JSONObject jsonResponse);

    void showErrorPresenter(JSONException e);
}
