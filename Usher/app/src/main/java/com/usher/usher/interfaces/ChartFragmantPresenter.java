package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatisticsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public interface ChartFragmantPresenter {

    void getChart(String username, String session, SessionStatisticsActivity sessionStatistics);

    void showErrorPresenter(JSONException e);

    void onReceiveStatisticsFailed();

    void onReceiveStatisticsSuccessfull(JSONObject jsonResponse) throws JSONException;
}
