package com.usher.usher.views;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.LoginActivity;
import com.usher.usher.R;
import com.usher.usher.RefreshRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SessionView extends View {

    /*private Paint paintDip1, paintDip2, paintDip3, paintDip4, paintDip5, paintDip6;

    InputStream is;
    BufferedReader b;*/
    String linea;
    Paint elemento;
    List<Paint> camara;

    public SessionView(Context context) {
        super(context);

        init(null, context);
    }

    public SessionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);


    }

    public SessionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    /*public SessionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }*/

    private void init(@Nullable AttributeSet set, final Context context){
        camara = new ArrayList<>();
        linea = "000111";
        int i;
        for (i = 0; i<linea.length(); i++){
            elemento = new Paint (Paint.ANTI_ALIAS_FLAG);
            elemento.setColor(Color.GRAY);
            camara.add (elemento);
        }

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            public void run() {


                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response){

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean succes = jsonResponse.getBoolean("succes");

                            if(succes){
                                linea = jsonResponse.getString("status");
                                postInvalidate();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RefreshRequest refreshRequest= new RefreshRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
                queue.add(refreshRequest);






            }
        }, 3 * 1000L, 3 * 1000L);//3 seconds
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int ancho = (canvas.getWidth()/2);
        int pos = 0;
        int presente = 0, ausente = 0;
        int i;
        for (i = 0; i<linea.length(); i++){
            camara.get(i).setColor(linea.charAt(i) == '0' ? Color.GREEN : Color.RED);
            if (linea.charAt(i) == '0')
                presente++;
            else ausente++;
            pos = pos +120;
            canvas.drawCircle(pos,100,50, camara.get(i));
        }

    }
}
