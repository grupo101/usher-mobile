package com.usher.usher;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RefreshRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://192.168.1.101:8080/Refresh.php";
    //private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/login?token=48370255gBrgdlpl050588";
    //private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-bck/login.php";
    private Map<String,String> params;

    public RefreshRequest(Response.Listener<String> listener ){

        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}