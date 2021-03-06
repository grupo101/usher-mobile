package com.usher.usher.interfaces;

public interface MainMenuActivityView {

    void showProgress(boolean option);

    void onSesion(boolean status);

    void offSesion(boolean status);

    void accessSuccessfullView();

    void accessFailedView();

    void showError();

    void showName(String name_show);
}
