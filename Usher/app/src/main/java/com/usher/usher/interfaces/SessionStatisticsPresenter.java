package com.usher.usher.interfaces;

import org.json.JSONObject;

public interface SessionStatisticsPresenter {
    void fillSpinner(String username);

    void listSessions(JSONObject jsonResponse);
}
