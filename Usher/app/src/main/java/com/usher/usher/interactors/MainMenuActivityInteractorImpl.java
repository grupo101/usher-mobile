package com.usher.usher.interactors;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.MainMenuActivityInteractor;
import com.usher.usher.interfaces.MainMenuActivityPresenter;
import com.usher.usher.requests.LoginActivityRequest;
import com.usher.usher.requests.SessionListRequest;
import com.usher.usher.requests.StatisticsAccessRequest;
import com.usher.usher.views.MainMenuActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MainMenuActivityInteractorImpl implements MainMenuActivityInteractor {


    private MainMenuActivityPresenter presenter;
    private Response.Listener<String> responseListener;

    public MainMenuActivityInteractorImpl(MainMenuActivityPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void checkSesionStatus(String username, MainMenuActivity mainMenuActivity) {
        responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes)
                        presenter.onSesion(true);
                    else
                        presenter.offSesion(false);
                    //BORRAR POST EXPO
                        presenter.onSesion(true);
                } catch (JSONException e) {
                    presenter.showErrorPresenter(e);
                }
            }
        };
        SessionListRequest activeSessionRequest = new SessionListRequest(true, username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(mainMenuActivity);
        queue.add(activeSessionRequest);
    }

    @Override
    public void doAccessValidation(String username, MainMenuActivity mainMenuActivity){
        responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes)
                        presenter.accessSuccessfull();
                    else
                        presenter.accessFailed();
                } catch (JSONException e) {
                    presenter.showErrorPresenter(e);
                }
            }
        };
        StatisticsAccessRequest accessRequest = new StatisticsAccessRequest(username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(mainMenuActivity);
        queue.add(accessRequest);
    }

}
