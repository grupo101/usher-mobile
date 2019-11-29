package com.usher.usher.requests;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivityRequest extends StringRequest {

    //private static final String LOGIN_REQUEST_URL="http://192.168.43.229:8080/Login.php";
    private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/login?token=48370255gBrgdlpl050588";
    //private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/login?token=48370255gBrgdlpl050588";
    private Map<String,String> params;

    public LoginActivityRequest(String username, String password, Response.Listener<String> listener ){

        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("username",username);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
