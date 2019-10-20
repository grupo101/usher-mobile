package com.usher.usher.interfaces;

import com.usher.usher.views.ChartFragment;
import com.usher.usher.views.SessionStatistics;

public interface ChartFragmentInteractor {
    void getSessionData(String username, String session, ChartFragment view, SessionStatistics sessionStatistics);
}
