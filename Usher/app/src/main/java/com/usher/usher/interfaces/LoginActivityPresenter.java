package com.usher.usher.interfaces;

import com.usher.usher.views.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public interface LoginActivityPresenter {

    void doLogin(String username, String password);

    void onLoginSuccessfull(JSONObject response) throws JSONException;

    void onLoginFailed(JSONObject response) throws JSONException;

    void showErrorPresenter(JSONException error);

}
