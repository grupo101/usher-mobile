package com.usher.usher.requests;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL="https://usher.sytes.net/usher-api/register?token=48370255gBrgdlpl050588";
    //private static final String REGISTER_REQUEST_URL="http://192.168.0.6:8080/Register.php";
    private Map<String,String> params;

    public RegisterUserRequest(String name, String surname, String username, String password, String access, Response.Listener<String> listener ){

        super(Method.POST,REGISTER_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("name",name);
        params.put("surname",surname);
        params.put("username",username);
        params.put("password",password);
        params.put("access",access);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
