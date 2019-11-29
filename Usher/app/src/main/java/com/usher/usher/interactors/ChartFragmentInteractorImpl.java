package com.usher.usher.interactors;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.ChartFragmantPresenter;
import com.usher.usher.interfaces.ChartFragmentInteractor;
import com.usher.usher.presenters.ChartFragmentPresenterImpl;
import com.usher.usher.requests.BlockStatisticsRequest;
import com.usher.usher.views.SessionStatisticsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ChartFragmentInteractorImpl implements ChartFragmentInteractor {

    private ChartFragmantPresenter presenter;
    private Response.Listener<String> responseListener;

    public ChartFragmentInteractorImpl(ChartFragmentPresenterImpl presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getSessionData(String username, String session, SessionStatisticsActivity sessionStatistics) {
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
        BlockStatisticsRequest blockStatisticsRequest = new BlockStatisticsRequest(username, session, responseListener);
        RequestQueue queue = Volley.newRequestQueue(sessionStatistics);
        queue.add(blockStatisticsRequest);
    }
}
