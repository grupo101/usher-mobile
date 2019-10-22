package com.usher.usher.presenters;

import android.util.Log;

import com.usher.usher.interactors.RepresentativeInteractorImpl;
import com.usher.usher.interfaces.RepresentativeInteractor;
import com.usher.usher.interfaces.RepresentativePresenter;
import com.usher.usher.interfaces.RepresentativeView;
import com.usher.usher.models.RepresentativeVO;
import com.usher.usher.views.RepresentativeFragment;
import com.usher.usher.views.SessionStatisticsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RepresentativePresenterImpl implements RepresentativePresenter {

    private RepresentativeView view;
    private RepresentativeInteractor interactor;
    private ArrayList<RepresentativeVO> listRepresentative;
    private String photo, name, surname, block;
    private Integer presences, total;


    public RepresentativePresenterImpl(RepresentativeFragment view) {
        //Por aca recibe lo de la vista con sus parametros como constructor
        this.view = view;
        //Por aca paso los metodos del presentador al interactor
        interactor = new RepresentativeInteractorImpl(this);
    }

    @Override
    public void loadRepresentative(String username, String session, SessionStatisticsActivity sessionStatistics) {
        interactor.loadRepresentativeData(username, session, sessionStatistics);
    }

    @Override
    public void onReceiveStatisticsSuccessfull(JSONObject response) throws JSONException {


        if (view != null) {
            //Codigo de carga de datos en la lista
            listRepresentative = new ArrayList<>();

            for (int i = 0; i < response.length() - 2; i++) {
                photo = response.getJSONObject(Integer.toString(i)).getString("block");
                name = response.getJSONObject(Integer.toString(i)).getString("member_name");
                surname = response.getJSONObject(Integer.toString(i)).getString("member_surname");
                block = response.getJSONObject(Integer.toString(i)).getString("block");
                presences = response.getJSONObject(Integer.toString(i)).getInt("presences");
                total = response.getJSONObject(Integer.toString(i)).getInt("total");
                listRepresentative.add( new RepresentativeVO( photo, name + " " + surname, block, presences, total));
                listRepresentative.add( new RepresentativeVO( photo, name + " " + surname, block, presences, total));
                listRepresentative.add( new RepresentativeVO( photo, name + " " + surname, block, presences, total));
                listRepresentative.add( new RepresentativeVO( photo, name + " " + surname, block, presences, total));
                listRepresentative.add( new RepresentativeVO( photo, name + " " + surname, block, presences, total));
            }
            view.showRepresentativeView(listRepresentative);
        }
    }

    @Override
    public void onReceiveStatisticsFailed() {
        if (view != null) {
            view.showRepresentativeFailed();
        }
    }

    @Override
    public void showErrorPresenter(JSONException error) {
        if (view != null) {
            error.printStackTrace();
            view.showError();
        }
    }
}
