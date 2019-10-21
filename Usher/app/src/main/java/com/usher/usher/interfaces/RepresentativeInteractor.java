package com.usher.usher.interfaces;

import com.usher.usher.views.SessionStatisticsActivity;

public interface RepresentativeInteractor {
    void loadRepresentativeData(String username, String session, SessionStatisticsActivity sessionStatistics);
}
