package com.usher.usher.presenters;

import com.usher.usher.interactors.RegisterUserActivityInteractorImpl;
import com.usher.usher.interfaces.RegisterUserActivityInteractor;
import com.usher.usher.interfaces.RegisterUserActivityPresenter;
import com.usher.usher.interfaces.RegisterUserActivityView;
import com.usher.usher.views.RegisterUserActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUserActivityPresenterImpl implements RegisterUserActivityPresenter {

    private RegisterUserActivityView view;
    private RegisterUserActivityInteractor interactor;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String access;


    public RegisterUserActivityPresenterImpl(RegisterUserActivity view) {
        this.view = view;
        interactor = new RegisterUserActivityInteractorImpl(this);
    }


    @Override
    public void doRegister(String name, String surname, String username, String password, String access) {
        view.showProgress(true);
        if (!name.isEmpty() && !surname.isEmpty() && !username.isEmpty() && !password.isEmpty() && !access.isEmpty()) {
            this.name = name;
            this.surname = surname;
            this.username = username;
            this.password = password;
            this.access = access;
            interactor.doRegisterValidations(this.name, this.surname, this.username, this.password, this.access, (RegisterUserActivity) view);
        } else if (name.isEmpty()) {
            view.showProgress(false);
            view.onNameClean();
        } else if (surname.isEmpty()) {
            view.showProgress(false);
            view.onSurnameClean();
        } else if (username.isEmpty()) {
            view.showProgress(false);
            view.onUsernameClean();
        } else if (password.isEmpty()) {
            view.showProgress(false);
            view.onPasswordClean();
        }  else {
            view.showProgress(false);
            view.onFailedDataCharge();
        }
    }

    @Override
    public void onRegisterSuccessfull(JSONObject jsonResponse) {
        view.showProgress(false);
        view.onRegisterSuccessfull();
    }

    @Override
    public void onRegisterFailed(JSONObject jsonResponse) {
        if (view != null) {
            view.showProgress(false);
            view.onRegisterFailed();
        }
    }

    @Override
    public void showErrorPresenter(JSONException e) {
        if (view != null) {
            e.printStackTrace();
            view.showError();
        }
    }
}
