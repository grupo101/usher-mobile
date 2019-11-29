package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatisticsActivity;

public interface SessionStatisticsInteractor {
    void getSessionList(String username, SessionStatisticsActivity sessionStatistics);
}
