package com.usher.usher.interfaces;

public interface MainMenuActivityPresenter {

    void checkSesion();

    void onSesion(boolean status);

    void offSesion(boolean status);
}
