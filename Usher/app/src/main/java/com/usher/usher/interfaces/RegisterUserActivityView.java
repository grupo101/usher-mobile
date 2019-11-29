package com.usher.usher.interfaces;

public interface RegisterUserActivityView {

    void showProgress(boolean option);

    void onRegisterSuccessfull();

    void onRegisterFailed();

    void showError();

    void onFailedDataCharge();

    void onNameClean();

    void onSurnameClean();

    void onUsernameClean();

    void onPasswordClean();
}
