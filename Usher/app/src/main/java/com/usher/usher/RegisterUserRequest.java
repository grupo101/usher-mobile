package com.usher.usher;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserRequest extends StringRequest {

    //private static final String REGISTER_REQUEST_URL="http://10.20.49.210:8080/register.php";
    private static final String REGISTER_REQUEST_URL="http://192.168.1.101:8080/Register.php";
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
