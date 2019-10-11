package com.usher.usher.interactors;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.SessionStatisticsInteractor;
import com.usher.usher.interfaces.SessionStatisticsPresenter;
import com.usher.usher.requests.SessionListRequest;
import com.usher.usher.requests.StatisticsAccessRequest;
import com.usher.usher.views.SessionStatistics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SessionStatisticsInteractorImpl implements SessionStatisticsInteractor {

    private SessionStatisticsPresenter presenter;
    private Response.Listener<String> responseListener;

    public SessionStatisticsInteractorImpl(SessionStatisticsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getSessionList(String username, SessionStatistics sessionStatistics) {
        responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    //JSONArray jsonArray = new JSONArray(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes)
                            presenter.listSessions(jsonResponse);
                    //else
                        //presenter.accessFailed();
                } catch (JSONException e) {
                    //presenter.showErrorPresenter(e);
                }
            }
        };
        SessionListRequest sessionListRequest = new SessionListRequest(username, responseListener);
        RequestQueue queue = Volley.newRequestQueue(sessionStatistics);
        queue.add(sessionListRequest);

    }
}
