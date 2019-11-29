package com.usher.usher.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.usher.usher.R;
import com.usher.usher.views.SessionView;



public class OpenSessionActivity extends AppCompatActivity {

    public TextView tvPres;
    private TextView tvAus;
    public int tam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_session);
        final Intent openSession = getIntent();

        tam = openSession.getIntExtra("tamano", -1);

        SessionView sessionView = new SessionView(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home)
            this.finish();

        return super.onOptionsItemSelected(item);
    }

    public int getTam(){ return tam; }
}
