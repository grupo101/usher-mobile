package com.usher.usher;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="http://192.168.0.108/register.php";
    private Map<String,String> params;

    public RegisterUserRequest(String name, String surName, String userName, String password, Response.Listener<String> listener ){

        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("name",name);
        params.put("surName",surName);
        params.put("userName", userName);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
