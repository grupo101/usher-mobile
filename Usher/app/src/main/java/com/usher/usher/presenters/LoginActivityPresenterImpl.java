package com.usher.usher.presenters;

import com.usher.usher.interactors.LoginActivityInteractorImpl;
import com.usher.usher.interfaces.LoginActivityInteractor;
import com.usher.usher.interfaces.LoginActivityPresenter;
import com.usher.usher.interfaces.LoginActivityView;
import com.usher.usher.views.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivityPresenterImpl implements LoginActivityPresenter {

    private LoginActivityView view;
    private LoginActivityInteractor interactor;
    private String name;
    private String surname;
    private String username;
    private String password;

    public LoginActivityPresenterImpl(LoginActivityView view) {
        //Por aca recibe lo de la vista con sus parametros como constructor
        this.view = view;
        //Por aca paso los metodos del presentador al interactor
        interactor = new LoginActivityInteractorImpl(this);
    }


    @Override
    public void doLogin(String username, String password) {
        view.showProgress(true);
        if ( !username.isEmpty() && !password.isEmpty() ) {
            this.username = username;
            this.password = password;
            interactor.doLoginValidations(this.username, this.password, (LoginActivity) view);
        }
        else if ( username.isEmpty() ){
            view.showProgress(false);
            view.onUsernameClean();
        }
        else if ( password.isEmpty() ){
            view.showProgress(false);
            view.onPasswordClean();
        }else{
            view.showProgress(false);
            view.onFailedDataCharge();
        }
    }

    @Override
    public void onLoginSuccessfull(JSONObject response) throws JSONException {
        if (view != null) {
            name = response.getString("name");
            surname = response.getString("surname");
            view.showProgress(false);
            view.onLoginSuccessfullView(name, surname, username, password);
        }
    }

    @Override
    public void onLoginFailed(JSONObject response) {
        if (view != null) {
            view.showProgress(false);
            view.showLoginFailed();
        }
    }

    @Override
    public void showErrorPresenter(JSONException error) {
        if (view != null) {
            view.showProgress(false);
            error.printStackTrace();
            view.showError();
        }
    }
}
