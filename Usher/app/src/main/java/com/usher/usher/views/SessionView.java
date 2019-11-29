package com.usher.usher.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
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
    Paint numberSeats, marginLines;
    List<Paint> camara;
    int pos;
    int taam;
    Button btn_Quorum, btn_Pres, btn_Aus;
    private boolean isRender = false;
    private boolean isCamaraNotSet = true;


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

    private void init(@Nullable AttributeSet set, final Context context) {
        camara = new ArrayList<>();
        btn_Aus = ((OpenSessionActivity) context).findViewById(R.id.btn_aus);
        btn_Pres = ((OpenSessionActivity) context).findViewById(R.id.btn_pres);
        btn_Quorum = ((OpenSessionActivity) context).findViewById(R.id.btn_Quorum);
        numberSeats = new Paint();
        numberSeats.setColor(Color.BLACK);
        numberSeats.setTextSize(20);
        marginLines = new Paint();
        marginLines.setColor(Color.BLACK);
        final RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        RefreshRequest refreshRequest;
        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean succes = jsonResponse.getBoolean("succes");

                            if (succes) {
                                linea = jsonResponse.getString("status");
                                taam = linea.length();
                                int i, presente = 0, ausente = 0;
                                for (i = 0; i < linea.length(); i++) {

                                    if (linea.charAt(i) == '1')
                                        presente++;
                                    else ausente++;

                                    if (isCamaraNotSet) {
                                        elemento = new Paint(Paint.ANTI_ALIAS_FLAG);
                                        elemento.setColor(Color.GRAY);
                                        camara.add(elemento);
                                    }
                                }
                                isCamaraNotSet = false;
                                if (presente > ausente) {
                                    if (btn_Quorum != null)
                                        btn_Quorum.setBackground(getResources().getDrawable(R.drawable.btn_green));

                                } else {
                                    if (btn_Quorum != null)
                                        btn_Quorum.setBackground(getResources().getDrawable(R.drawable.btn_red));
                                }
                                if (btn_Pres != null) {
                                    btn_Pres.setText(String.valueOf(presente));
                                }
                                if (btn_Aus != null) {
                                    btn_Aus.setText(String.valueOf(ausente));
                                }
                                setRender(true);
                                postInvalidate();
                            } else
                                setRender(false);
                        } catch (JSONException e) {
                            //e.printStackTrace();
                        }
                    }
                };

                RefreshRequest refreshRequest = new RefreshRequest(responseListener);
                queue.add(refreshRequest);

            }
        }, 0, 3 * 1000L);//3 seconds
    }

    public void setRender(boolean render) {
        isRender = render;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int ancho = getWidth();
        int alto = getHeight();
        int desply = 0;
        canvas.drawLine(25, 25, ancho - 25, 25, marginLines);
        canvas.drawLine(ancho - 25, 25, ancho - 25, alto - 25, marginLines);
        canvas.drawLine(ancho - 25, alto - 25, 25, alto - 25, marginLines);
        canvas.drawLine(25, alto - 25, 25, 25, marginLines);
        pos = 0;
        int i;
        if (isRender) {
            if (taam != 9){
                for (i = 0; i < taam; i++) {
                    camara.get(i).setColor(linea.charAt(i) == '1' ? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.colorPrimary));

                    if ((pos + ancho / 10) < ancho) {
                        pos = pos + ancho / 11;
                    } else {
                        pos = ancho / 11;
                        desply = desply + alto / 11;
                    }

                    canvas.drawCircle(pos, alto / 11 + desply, ancho / 30, camara.get(i));

                    String ubicacion = Integer.toString(i + 1);
                    if (i < 9)
                        canvas.drawText(ubicacion, pos - 5, (alto / 11) + desply + 10, numberSeats);
                    else if (i < 99)
                        canvas.drawText(ubicacion, pos - 13, (alto / 11) + desply + 10, numberSeats);
                    else
                        canvas.drawText(ubicacion, pos - 23, (alto / 11) + desply + 10, numberSeats);
                }
            }else {
                numberSeats.setTextSize(50);

                desply = alto;
                for (i = 0; i < taam; i++) {
                    camara.get(i).setColor(linea.charAt(i) == '1' ? getResources().getColor(R.color.colorAccent) : getResources().getColor(R.color.colorPrimary));
                    if ((pos + ancho / 4) < ancho) {
                        pos = pos + ancho / 4;
                    } else {
                        pos = ancho / 4;
                        desply = desply - alto / 4;
                    }
                    canvas.drawCircle(pos, desply - alto / 4, ancho / 10, camara.get(i));
                    canvas.drawText(Integer.toString(i + 1), pos - 15,  desply - (alto / 4) + 15, numberSeats);
                }
            }
        }
    }
}
