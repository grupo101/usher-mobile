package com.usher.usher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etSurName, etUser, etPassword;
    Button registerBtn;

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

        registerBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        final String name = etName.getText().toString();
        final String surName = etSurName.getText().toString();
        final String userName = etUser.getText().toString();
        final String password = etPassword.getText().toString();
        final String access = "false";
        //final String birth = etBirth.getText().toString();
        //final int phone = Integer.parseInt(etPhone.getText().toString());

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    if (succes) {
                        Intent intent = new Intent(RegisterUser.this, LoginActivity.class);
                        RegisterUser.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterUser.this);
                        builder.setMessage("Error en el registro").setNegativeButton("Retry", null).create().show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        };
        RegisterUserRequest registerUserRequest = new RegisterUserRequest(name, surName, userName, password, access, responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterUser.this);
        queue.add(registerUserRequest);
    }
}
