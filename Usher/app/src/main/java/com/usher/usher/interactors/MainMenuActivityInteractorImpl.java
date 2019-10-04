package com.usher.usher.interactors;

import com.usher.usher.interfaces.MainMenuActivityInteractor;
import com.usher.usher.interfaces.MainMenuActivityPresenter;

public class MainMenuActivityInteractorImpl implements MainMenuActivityInteractor {


    private MainMenuActivityPresenter presenter;

    public MainMenuActivityInteractorImpl(MainMenuActivityPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void checkSesionStatus() {
        //aca va el codigo para pegarle a la sesion para saber si esta en START
        if(true)
            presenter.onSesion(true);
        else
            presenter.offSesion(false);
    }
}
