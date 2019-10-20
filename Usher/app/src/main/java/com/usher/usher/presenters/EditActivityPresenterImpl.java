package com.usher.usher.presenters;

import com.usher.usher.interactors.EditActivityInteractorImpl;
import com.usher.usher.interfaces.EditActivityInteractor;
import com.usher.usher.interfaces.EditActivityPresenter;
import com.usher.usher.interfaces.EditActivityView;
import com.usher.usher.views.EditActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class EditActivityPresenterImpl implements EditActivityPresenter {

    private EditActivityView view;
    private EditActivityInteractor interactor;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String newPassword;
    private String error;

    public EditActivityPresenterImpl(EditActivityView view) {

        this.view = view;

        interactor = new EditActivityInteractorImpl(this);
    }

    @Override
    public void doUpdate(String name, String surname, String username, String password, String newPassword) {
        view.showProgress(true);
        if ( !username.isEmpty() && !password.isEmpty() ) {
            this.name = name;
            this.surname = surname;
            this.username = username;
            this.password = password;
            this.newPassword = newPassword;
            interactor.updateProfile(this.name,this.surname, this.username, this.password, this.newPassword, (EditActivity) view);
        }
    }

    @Override
    public void updateSuccessfull(JSONObject response) throws JSONException {
        if (view != null) {
            view.showProgress(false);
            view.onUpdateSuccessfullView(name, surname, username, password);
        }
    }

    @Override
    public void updateFailed(JSONObject response) throws JSONException {
        if (view != null) {
            error = response.getString("error");
            view.showProgress(false);
            view.showUpdateFailed(error);
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
