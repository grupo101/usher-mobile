package com.usher.usher.interfaces;

public interface EditActivityView {
    void onUpdateSuccessfullView(String name, String surname, String username, String password);

    void showUpdateFailed(String error);

    void showProgress(boolean b);

    void showError();
}
