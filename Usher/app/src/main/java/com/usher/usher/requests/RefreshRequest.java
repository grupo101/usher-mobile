package com.usher.usher.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RefreshRequest extends StringRequest {

    private static final String REFRESH_REQUEST_URL="https://usher.sytes.net/usher-api/check_status?token=48370255gBrgdlpl050588";
    //private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/refresh?token=48370255gBrgdlpl050588";
    private Map<String,String> params;

    public RefreshRequest(Response.Listener<String> listener ){

        super(Request.Method.POST,REFRESH_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("server","SVR1");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
