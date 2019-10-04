package com.usher.usher.interfaces;

public interface LoginActivityView {

    void showProgress(boolean option);

    void showResult(String result);

    void onLoginSuccessfullView(String name, String surname, String username, String password);

    void showLoginFailed();

    void showError();

    void onPasswordClean();

    void onUsernameClean();
}
