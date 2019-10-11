package com.usher.usher.presenters;


import com.usher.usher.interactors.SessionStatisticsInteractorImpl;
import com.usher.usher.interfaces.SessionStatisticsInteractor;
import com.usher.usher.interfaces.SessionStatisticsPresenter;
import com.usher.usher.interfaces.SessionStatisticsView;
import com.usher.usher.views.SessionStatistics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    public void listSessions (JSONObject jsonResponse) throws JSONException {
        int z = jsonResponse.length();
        ArrayList<String> arrayList = new ArrayList<String>();
        String coment = new String();
        for (int i = 1; i < z; i++) {
            if (i > 0)
                coment = jsonResponse.getJSONObject(Integer.toString(i)).getString("comment");
                arrayList.add(coment);
                view.loadList(arrayList);
        }
    }

}
