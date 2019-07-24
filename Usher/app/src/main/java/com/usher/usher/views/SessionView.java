package com.usher.usher.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.usher.usher.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SessionView extends View {

    private Paint paintDip1, paintDip2, paintDip3, paintDip4, paintDip5, paintDip6;
    String linea;
    InputStream is;
    BufferedReader b;

    public SessionView(Context context) {
        super(context);

        init(null);
    }

    public SessionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SessionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /*public SessionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }*/

    private void init(@Nullable AttributeSet set){
        paintDip1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintDip2 = new Paint();
        paintDip3 = new Paint();
        paintDip4 = new Paint();
        paintDip5 = new Paint();
        paintDip6 = new Paint();

        paintDip1.setColor(Color.GRAY);
        paintDip2.setColor(Color.GRAY);
        paintDip3.setColor(Color.GRAY);
        paintDip4.setColor(Color.GRAY);
        paintDip5.setColor(Color.GRAY);
        paintDip6.setColor(Color.GRAY);

        //b = new BufferedReader(new InputStreamReader(is));

    }

    @Override
    protected void onDraw(Canvas canvas) {

        int ancho = (canvas.getWidth()/2);
        char pos;
        //canvas.drawCircle(ancho,100,50, paintDip3);
        //canvas.drawCircle((ancho - 120),150,50, paintDip1);
        //canvas.drawCircle((ancho + 120),150,50, paintDip2);
        is = this.getResources().openRawResource(R.raw.status);
        b = new BufferedReader(new InputStreamReader(is));
        try {
            linea = b.readLine();
            paintDip1.setColor(linea.charAt(0) == '0' ? Color.GREEN : Color.RED);
            paintDip2.setColor(linea.charAt(1) == '0' ? Color.GREEN : Color.RED);
            paintDip3.setColor(linea.charAt(2) == '0' ? Color.GREEN : Color.RED);
            paintDip4.setColor(linea.charAt(3) == '0' ? Color.GREEN : Color.RED);
            paintDip5.setColor(linea.charAt(4) == '0' ? Color.GREEN : Color.RED);
            paintDip6.setColor(linea.charAt(5) == '0' ? Color.GREEN : Color.RED);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if(is!=null){
            try {
                if (((linea=b.readLine())!=null)) {
                    paintDip1.setColor(linea.charAt(0) == '0' ? Color.GREEN : Color.RED);
                    paintDip2.setColor(linea.charAt(1) == '0' ? Color.GREEN : Color.RED);
                    paintDip3.setColor(linea.charAt(2) == '0' ? Color.GREEN : Color.RED);
                    paintDip4.setColor(linea.charAt(3) == '0' ? Color.GREEN : Color.RED);
                    paintDip5.setColor(linea.charAt(4) == '0' ? Color.GREEN : Color.RED);
                    paintDip6.setColor(linea.charAt(5) == '0' ? Color.GREEN : Color.RED);
        //is.close();
               }
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            canvas.drawCircle(ancho,100,50, paintDip2);
            canvas.drawCircle((ancho - 120),150,50, paintDip1);
            canvas.drawCircle((ancho + 120),150,50, paintDip3);
            canvas.drawCircle(ancho,230,50, paintDip5);
            canvas.drawCircle((ancho - 120),280,50, paintDip4);
            canvas.drawCircle((ancho + 120),280,50, paintDip6);
        //}
    }
}
