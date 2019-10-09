package com.usher.usher.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StatisticsAccessRequest extends StringRequest {

    //ACA VA la ruta de php de acceso....momentaneamente pongo el de login para que devuelva TRUE
    private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/login?token=48370255gBrgdlpl050588";

    private Map<String,String> params;

    public StatisticsAccessRequest(String username, Response.Listener<String> listener) {
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("username",username);
        //Eliminar cuando exista la URL de access
        params.put("password","12345");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
