package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatistics;

import org.json.JSONException;
import org.json.JSONObject;

public interface ChartFragmantPresenter {

    void getChart(String method, String username, String session, SessionStatistics sessionStatistics);

    void showErrorPresenter(JSONException e);

    void onReceiveStatisticsFailed();

    void onReceiveStatisticsSuccessfull(JSONObject jsonResponse) throws JSONException;
}
