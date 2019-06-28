package com.usher.usher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    TextView tv_registerUser;
    EditText et_userLogin,et_passwordLogin;
    Button btn_loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    tv_registerUser= findViewById(R.id.tvRegLogin);
    btn_loginUser= findViewById(R.id.btnIniLogin);
    et_userLogin= findViewById(R.id.etUserLogin);
    et_passwordLogin= findViewById(R.id.etPassLogin);

    tv_registerUser.setOnClickListener(new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            Intent intentRegUser = new Intent(LoginActivity.this, RegisterUser.class);
            LoginActivity.this.startActivity(intentRegUser);
        }
    });


    btn_loginUser.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){

            final String userName = et_userLogin.getText().toString();
            final String password = et_passwordLogin.getText().toString();
            Response.Listener<String> responseListener = new Response.Listener<String>(){

                @Override
                public void onResponse(String response){

                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean success = jsonResponse.getBoolean("success");
                        if(success){
                            String name= jsonResponse.getString("name");
                            String surname= jsonResponse.getString("surName");

                            Intent intent= new Intent(LoginActivity.this,Dashboard.class);
                            intent.putExtra("name",name);
                            intent.putExtra("surname",surname);
                            intent.putExtra("userName",userName);
                            intent.putExtra("password",password);

                            LoginActivity.this.startActivity(intent);


                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("error en el login").setNegativeButton("reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

            };

            LoginActivityRequest loginActivityRequest= new LoginActivityRequest(userName,password, responseListener);

            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(loginActivityRequest);
        }
    });


    }



}