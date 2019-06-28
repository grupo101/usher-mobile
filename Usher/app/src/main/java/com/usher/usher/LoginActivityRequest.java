package com.usher.usher;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://192.168.0.107/login.php";
    private Map<String,String> params;

    public LoginActivityRequest(String userName, String password, Response.Listener<String> listener ){

        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("userName", userName);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
