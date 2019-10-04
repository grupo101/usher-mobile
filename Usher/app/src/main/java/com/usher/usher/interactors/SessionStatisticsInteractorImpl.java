package com.usher.usher.interactors;

import com.usher.usher.interfaces.SessionStatisticsInteractor;
import com.usher.usher.interfaces.SessionStatisticsPresenter;

public class SessionStatisticsInteractorImpl implements SessionStatisticsInteractor {

    private SessionStatisticsPresenter presenter;

    public SessionStatisticsInteractorImpl(SessionStatisticsPresenter presenter) {
        this.presenter = presenter;
    }
}
