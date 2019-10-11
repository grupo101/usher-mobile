package com.usher.usher.presenters;


import com.usher.usher.interactors.SessionStatisticsInteractorImpl;
import com.usher.usher.interfaces.SessionStatisticsInteractor;
import com.usher.usher.interfaces.SessionStatisticsPresenter;
import com.usher.usher.interfaces.SessionStatisticsView;
import com.usher.usher.views.SessionStatistics;

import org.json.JSONObject;

public class SessionStatisticsPresenterImpl implements SessionStatisticsPresenter {

    private SessionStatisticsView view;
    private SessionStatisticsInteractor interactor;

    public SessionStatisticsPresenterImpl( SessionStatisticsView view) {
        //Por aca recibe lo de la vista con sus parametros como constructor
        this.view = view;
        //Por aca paso los metodos del presentador al interactor
        interactor = new SessionStatisticsInteractorImpl(this);
    }

    @Override
    public void fillSpinner(String username) {
        interactor.getSessionList(username, (SessionStatistics) view);
    }

    @Override
    public void listSessions (JSONObject jsonResponse){

    }
}
