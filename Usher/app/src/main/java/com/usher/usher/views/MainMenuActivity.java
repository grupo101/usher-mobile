package com.usher.usher.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.usher.usher.R;
import com.usher.usher.interfaces.MainMenuActivityPresenter;
import com.usher.usher.interfaces.MainMenuActivityView;
import com.usher.usher.presenters.MainMenuActivityPresenterImpl;

public class MainMenuActivity extends AppCompatActivity implements MainMenuActivityView {


    private TextView tvName;
    private Button btn_sesion, btn_out, btn_hist, btn_edit;
    private ProgressBar pr_progressMainMenu;
    private MainMenuActivityPresenter presenter;
    private String username, password, name, surname;
    private static final int EDIT_ACTIVITY_REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvName = findViewById(R.id.tvNameDs);
        btn_sesion = findViewById(R.id.btn_sess);
        btn_out = findViewById(R.id.btn_out);
        btn_hist = findViewById(R.id.btn_historial);
        btn_edit = findViewById(R.id.btn_editprof);
        pr_progressMainMenu = findViewById(R.id.progressMainMenu);
        final Intent intent = getIntent();
        username = intent.getStringExtra("username");
        name = intent.getStringExtra("name");
        surname = intent.getStringExtra("surname");
        password = intent.getStringExtra("password");
        //Pasamos metodos del View al Presenter
        presenter = new MainMenuActivityPresenterImpl(this);

        presenter.setName(name, surname);

        btn_sesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.checkActiveSesion(intent.getStringExtra("username"));
            }
        });

        btn_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.checkAccess(intent.getStringExtra("username"));
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentEditProfile = new Intent(MainMenuActivity.this, EditActivity.class);
                intentEditProfile.putExtra("name", name);
                intentEditProfile.putExtra("surname", surname);
                intentEditProfile.putExtra("username", username);
                intentEditProfile.putExtra("password", password);
                MainMenuActivity.this.startActivityForResult(intentEditProfile, EDIT_ACTIVITY_REQUEST_CODE);
            }
        });
        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check that it is the EditActivity with an OK result
        if (requestCode == EDIT_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                name = data.getStringExtra("name");
                surname = data.getStringExtra("surname");
                password = data.getStringExtra("password");
                presenter.setName(name, surname);
            }
        }
    }

    @Override
    public void showProgress(boolean option) {
        pr_progressMainMenu.setVisibility(option ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSesion(boolean status) {
        Intent openSession = new Intent(MainMenuActivity.this, OpenSessionActivity.class);
        MainMenuActivity.this.startActivity(openSession);
    }

    @Override
    public void offSesion(boolean status) {
        Toast.makeText(getApplicationContext(), "Sesión innactiva. Intente en otro momento", Toast.LENGTH_LONG).show();
    }

    @Override
    public void accessSuccessfullView() {
        Intent sessionStatistics = new Intent(MainMenuActivity.this, SessionStatisticsActivity.class);
        sessionStatistics.putExtra("username", username);
        startActivity(sessionStatistics);
    }

    @Override
    public void accessFailedView() {
        Toast.makeText(getApplicationContext(), "No tiene permiso para acceder", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getApplicationContext(), "Error en la conexion", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showName(String welcome) {
        tvName.setText(welcome);
    }


}
