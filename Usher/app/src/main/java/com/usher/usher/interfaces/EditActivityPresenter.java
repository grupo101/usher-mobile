package com.usher.usher.interfaces;

import org.json.JSONException;
import org.json.JSONObject;

public interface EditActivityPresenter {

    void doUpdate(String name, String surname, String username, String password, String newPassword);

    void updateSuccessfull(JSONObject jsonResponse) throws JSONException;

    void updateFailed(JSONObject jsonResponse) throws JSONException;

    void showErrorPresenter(JSONException error);
}
