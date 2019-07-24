package com.usher.usher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.usher.usher.views.SessionView;

public class OpenSession extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_session);

        SessionView sessionView = new SessionView(this);
    }


}
