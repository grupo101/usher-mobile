package com.usher.usher.requests;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class EditActivityRequest extends StringRequest {

    private static final String EDIT_REQUEST_URL="https://usher.sytes.net/usher-api/usr_upd?token=48370255gBrgdlpl050588";
    private Map<String,String> params;

    public EditActivityRequest(String name, String surname, String username, String password, String newPassword, Response.Listener<String> listener ){

        super(Request.Method.POST,EDIT_REQUEST_URL,listener,null);
        params= new HashMap<>();
        params.put("name",name);
        params.put("surname",surname);
        params.put("username",username);
        params.put("password",password);
        params.put("newpass",newPassword);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }

}
