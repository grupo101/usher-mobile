package com.usher.usher.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class BlockStatisticsRequest extends StringRequest {

    private static final String BLOCK_REQUEST_URL="https://usher.sytes.net/usher-api/block_hist?token=48370255gBrgdlpl050588";
    private Map<String,String> params;

    public BlockStatisticsRequest (String username, String session, Response.Listener<String> listener ){

        super(Request.Method.POST,BLOCK_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("username",username);
        params.put("session",session);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}