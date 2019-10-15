package com.usher.usher.interactors;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.interfaces.EditActivityInteractor;
import com.usher.usher.interfaces.EditActivityPresenter;
import com.usher.usher.requests.EditActivityRequest;
import com.usher.usher.views.EditActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class EditActivityInteractorImpl implements EditActivityInteractor {

    private EditActivityPresenter presenter;
    private Response.Listener<String> responseListener;

    public EditActivityInteractorImpl(EditActivityPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void updateProfile (final String name, final String surname, final String username, final String password, final String newPassword, EditActivity editActivity) {
        responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes)
                        presenter.updateSuccessfull(jsonResponse);
                    else
                        presenter.updateFailed(jsonResponse);
                } catch (JSONException e) {
                    presenter.showErrorPresenter(e);
                }
            }
        };
        EditActivityRequest editActivityRequest = new EditActivityRequest(name, surname, username, password, newPassword, responseListener);
        RequestQueue queue = Volley.newRequestQueue(editActivity);
        queue.add(editActivityRequest);
    }
}
