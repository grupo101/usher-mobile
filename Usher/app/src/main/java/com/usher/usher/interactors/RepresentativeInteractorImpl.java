package com.usher.usher.interactors;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.RepresentativeInteractor;
import com.usher.usher.interfaces.RepresentativePresenter;
import com.usher.usher.requests.BlockStatisticsRequest;
import com.usher.usher.requests.RepresentativeStatisticsRequest;
import com.usher.usher.views.SessionStatistics;

import org.json.JSONException;
import org.json.JSONObject;

public class RepresentativeInteractorImpl implements RepresentativeInteractor {

    private RepresentativePresenter presenter;
    private Response.Listener<String> responseListener;

    public RepresentativeInteractorImpl(RepresentativePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadRepresentativeData(String username, String session, SessionStatistics sessionStatistics) {
        responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes)
                        presenter.onReceiveStatisticsSuccessfull(jsonResponse);
                    else
                        presenter.onReceiveStatisticsFailed();
                } catch (JSONException e) {
                    presenter.showErrorPresenter(e);
                }
            }
        };
        RepresentativeStatisticsRequest representativeRequest = new RepresentativeStatisticsRequest(username, session, responseListener);
        RequestQueue queue = Volley.newRequestQueue(sessionStatistics);
        queue.add(representativeRequest);

    }
}
