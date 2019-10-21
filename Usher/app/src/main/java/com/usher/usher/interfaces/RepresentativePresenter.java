package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatisticsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public interface RepresentativePresenter {

    void loadRepresentative(String username, String session, SessionStatisticsActivity sessionStatistics);

    void onReceiveStatisticsSuccessfull(JSONObject jsonResponse) throws JSONException;

    void onReceiveStatisticsFailed();

    void showErrorPresenter(JSONException e);
}
