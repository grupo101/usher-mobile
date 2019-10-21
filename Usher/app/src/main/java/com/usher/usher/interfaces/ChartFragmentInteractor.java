package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatisticsActivity;

public interface ChartFragmentInteractor {
    void getSessionData(String username, String session, SessionStatisticsActivity sessionStatistics);
}
