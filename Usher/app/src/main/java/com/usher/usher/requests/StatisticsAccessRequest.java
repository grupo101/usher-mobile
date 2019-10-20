package com.usher.usher.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class StatisticsAccessRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="https://usher.sytes.net/usher-api/block_head?token=48370255gBrgdlpl050588";

    private Map<String,String> params;

    public StatisticsAccessRequest(String username, Response.Listener<String> listener) {
        super(Request.Method.POST,LOGIN_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("username",username);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
