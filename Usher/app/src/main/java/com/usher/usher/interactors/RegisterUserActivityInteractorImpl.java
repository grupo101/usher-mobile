package com.usher.usher.interactors;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.RegisterUserActivityInteractor;
import com.usher.usher.interfaces.RegisterUserActivityPresenter;
import com.usher.usher.requests.RegisterUserRequest;
import com.usher.usher.views.RegisterUserActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUserActivityInteractorImpl implements RegisterUserActivityInteractor {

    private RegisterUserActivityPresenter presenter;

    public RegisterUserActivityInteractorImpl(RegisterUserActivityPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void doRegisterValidations(String name, String surname, String username, String password, String access, RegisterUserActivity registerActivity) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes) {
                        presenter.onRegisterSuccessfull(jsonResponse);
                    } else {
                        presenter.onRegisterFailed(jsonResponse);
                    }
                } catch (JSONException e) {
                    presenter.showErrorPresenter(e);
                }
            }
        };
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(name, surname, username, password, access, responseListener);
        RequestQueue queue = Volley.newRequestQueue(registerActivity);
        queue.add(registerUserRequest);
    }
}
