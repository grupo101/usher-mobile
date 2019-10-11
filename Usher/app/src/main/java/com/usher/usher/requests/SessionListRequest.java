package com.usher.usher.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SessionListRequest extends StringRequest {

    //ACA VA la ruta de php de lista de sesiones cerradas....momentaneamente pongo una URL local
    private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/sessions?token=48370255gBrgdlpl050588";

    private Map<String,String> params;

    public SessionListRequest(String username, Response.Listener<String> listener) {
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("username",username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
