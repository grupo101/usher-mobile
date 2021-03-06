package com.usher.usher.interfaces;

public interface LoginActivityView {

    void showProgress(boolean option);

    void onLoginSuccessfullView(String name, String surname, String username, String password);

    void showLoginFailed(boolean exists);

    void showError();

    void onPasswordClean();

    void onUsernameClean();

    void onFailedDataCharge();

}
