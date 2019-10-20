package com.usher.usher.views;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.R;
import com.usher.usher.interfaces.RegisterUserActivityPresenter;
import com.usher.usher.interfaces.RegisterUserActivityView;
import com.usher.usher.presenters.RegisterUserActivityPresenterImpl;
import com.usher.usher.requests.RegisterUserRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUserActivity extends AppCompatActivity
        implements RegisterUserActivityView {

    private EditText etName, etSurName, etUser, etPassword;
    private Button registerBtn;
    private RegisterUserActivityPresenter presenter;
    private ProgressBar pr_progresBarRegisterUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        etName = findViewById(R.id.etNameRG);
        etSurName = findViewById(R.id.etSurNameRG);
        etUser = findViewById(R.id.etUserRG);
        etPassword = findViewById(R.id.etPasswordRG);
        //etBirth = findViewById(R.id.etBirthRG);
        //etPhone = findViewById(R.id.etPhoneRG);

        registerBtn = findViewById(R.id.btnRegister);

        pr_progresBarRegisterUser = findViewById(R.id.progressRegister);

        //Pasamos metodos del View al Presenter
        presenter = new RegisterUserActivityPresenterImpl(this);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.doRegister(
                        etName.getText().toString(),
                        etSurName.getText().toString(),
                        etUser.getText().toString(),
                        etPassword.getText().toString(),
                        "0");
            }
        });
    }

    @Override
    public void showProgress(boolean option) {
        pr_progresBarRegisterUser.setVisibility(option ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRegisterSuccessfull() {
        Intent intent = new Intent(RegisterUserActivity.this, LoginActivity.class);
        RegisterUserActivity.this.startActivity(intent);
    }

    @Override
    public void onRegisterFailed() {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUserActivity.this);
        builder.setMessage("Error en el registro. Usuario existente").setNegativeButton("Retry", null).create().show();
    }

    @Override
    public void showError() {
        Toast.makeText( getApplicationContext(), "Error en la conexion", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailedDataCharge() {
        Toast.makeText( getApplicationContext(), "No ingreso ningun dato", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNameClean() {
        Toast.makeText( getApplicationContext(), "No ingreso ningun nombre", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSurnameClean() {
        Toast.makeText( getApplicationContext(), "No ingreso ningun nombre", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUsernameClean() {
        Toast.makeText( getApplicationContext(), "El usuario no fue ingresado", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPasswordClean() {
        Toast.makeText( getApplicationContext(), "No ingreso ningun apellido", Toast.LENGTH_LONG).show();
    }
}
