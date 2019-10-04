package com.usher.usher.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.usher.usher.OpenSession;
import com.usher.usher.R;
import com.usher.usher.requests.RefreshRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SessionView extends View {

    String linea;
    Paint elemento;
    Paint numberSeats, paintDefault;
    List<Paint> camara;
    int pos, presente, ausente;
    int taam;
    int[] TAM;
    Button btn_Quorum, btn_Pres, btn_Aus;
    boolean show;


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
        final int tam = 100;
        camara = new ArrayList<>();
        btn_Aus = ((OpenSession)context).findViewById(R.id.btn_aus);
        btn_Pres = ((OpenSession)context).findViewById(R.id.btn_pres);
        btn_Quorum = ((OpenSession)context).findViewById(R.id.btn_Quorum);
        TAM = new int[1];
        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("succes");
                    int i;
                    if (succes) {
                        show = true;
                        TAM[0] = jsonResponse.getString("status").length();
                        taam = TAM[0];

                        for (i = 0; i < taam; i++) {
                            elemento = new Paint(Paint.ANTI_ALIAS_FLAG);
                            elemento.setColor(Color.GRAY);
                            camara.add(elemento);
                        }
                    } else{
                        show = false;
                        for (i = 0; i < tam; i++) {
                            elemento = new Paint(Paint.ANTI_ALIAS_FLAG);
                            elemento.setColor(Color.GRAY);
                            camara.add(elemento);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RefreshRequest refreshRequest= new RefreshRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(this.getContext());
        queue.add(refreshRequest);

        numberSeats = new Paint();
        numberSeats.setColor(Color.BLACK);
        numberSeats.setTextSize(40);
        paintDefault = new Paint();
        paintDefault.setColor(Color.GRAY);

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
                                show = true;
                                linea = jsonResponse.getString("status");
                                taam = linea.length();
                                int i, presente = 0, ausente = 0;
                                for (i = 0; i<linea.length(); i++) {

                                    if (linea.charAt(i) == '0')
                                        presente++;
                                    else ausente++;
                                }
                                if (presente>ausente) {
                                    if (btn_Quorum != null)
                                        btn_Quorum.setBackgroundColor(Color.GREEN);
                                }else {
                                    if (btn_Quorum != null)
                                        btn_Quorum.setBackgroundColor(Color.RED);
                                }
                                if (btn_Pres !=null){
                                    btn_Pres.setText(String.valueOf(presente));
                                }
                                if (btn_Aus !=null){
                                    btn_Aus.setText(String.valueOf(ausente));
                                }
                                postInvalidate();

                            } else
                                show = false;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RefreshRequest refreshRequest= new RefreshRequest(responseListener);
                RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
                queue.add(refreshRequest);

            }
        }, 0, 3 * 1000L);//3 seconds
    }

    @Override
    protected void onDraw(Canvas canvas) {
    //public void onDraw(Canvas canvas) {
        int anchoMitad = (getWidth()/2);
        int ancho = getWidth();
        int alto = getHeight();
        int desply = 0;
        canvas.drawLine(50, 50, ancho - 50, 50, numberSeats);
        canvas.drawLine(ancho - 50, 50, ancho - 50, alto - 50, numberSeats);
        canvas.drawLine(ancho - 50, alto - 50, 50, alto - 50, numberSeats);
        canvas.drawLine(50, alto - 50, 50, 50, numberSeats);
        pos = 0;
        int i;
        for (i = 0; i < taam; i++) {
            if (camara.size() > 0)
                camara.get(i).setColor(linea.charAt(i) == '0' ? Color.GREEN : Color.RED);

            if ((pos + ancho / 10) < ancho) {
                pos = pos + ancho / 11;
            } else {
                pos = ancho / 11;
                desply = desply + ancho / 11;
            }
            if (camara.size() > 0)
                canvas.drawCircle(pos, ancho / 11 + desply, ancho / 30, camara.get(i));
            else
                canvas.drawCircle(pos, ancho / 11 + desply, ancho / 30, paintDefault);
            String ubicacion = Integer.toString(i + 1);
            if (i < 9)
                canvas.drawText(ubicacion, pos - 10, (ancho / 11) + desply + 10, numberSeats);
            else if (i < 99)
                canvas.drawText(ubicacion, pos - 20, (ancho / 11) + desply + 10, numberSeats);
            else
                canvas.drawText(ubicacion, pos - 30, (ancho / 11) + desply + 10, numberSeats);
        }
    }
}
