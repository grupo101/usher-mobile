package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatistics;

public interface SessionStatisticsInteractor {
    void getSessionList(String username, SessionStatistics sessionStatistics);
}
