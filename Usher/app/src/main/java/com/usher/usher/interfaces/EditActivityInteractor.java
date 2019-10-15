package com.usher.usher.interfaces;

import com.usher.usher.views.EditActivity;

public interface EditActivityInteractor {

    void updateProfile(String name, String surname, String username, String password, String newPassword, EditActivity editActivity);
}
