package com.usher.usher.requests;

import android.support.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

public class RepresentativeStatisticsRequest extends StringRequest {

    private static final String BLOCK_REQUEST_URL = "https://usher.sytes.net/usher-api/member_hist?token=48370255gBrgdlpl050588";
    private Map<String, String> params;

    public RepresentativeStatisticsRequest(String username, String session, Response.Listener<String> listener) {

        super(Request.Method.POST, BLOCK_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("session", session);
    }
}
