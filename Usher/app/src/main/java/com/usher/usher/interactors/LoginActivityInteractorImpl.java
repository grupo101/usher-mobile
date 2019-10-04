package com.usher.usher.interactors;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.LoginActivityInteractor;
import com.usher.usher.interfaces.LoginActivityPresenter;
import com.usher.usher.requests.LoginActivityRequest;
import com.usher.usher.views.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivityInteractorImpl implements LoginActivityInteractor {

    private LoginActivityPresenter presenter;
    private Response.Listener<String> responseListener;

    public LoginActivityInteractorImpl(LoginActivityPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void doLoginValidations(final String username, final String password) {
        responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes)
                        presenter.onLoginSuccessfull(jsonResponse);
                    else
                        presenter.onLoginFailed(jsonResponse);
                } catch (JSONException e) {
                    presenter.showErrorPresenter(e);
                }
            }
        };

    }

    @Override
    public void volleyNoEntiendoInteractor(String username, String password, LoginActivity loginActivity) {
        LoginActivityRequest loginActivityRequest = new LoginActivityRequest(username, password, responseListener);
        Log.i("LogIn", "LoginActivityRequest ");
        RequestQueue queue = Volley.newRequestQueue(loginActivity);
        queue.add(loginActivityRequest);
    }
}
