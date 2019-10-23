package com.usher.usher.presenters;

import android.graphics.Color;

import com.usher.usher.interactors.ChartFragmentInteractorImpl;
import com.usher.usher.interfaces.ChartFragmantPresenter;
import com.usher.usher.interfaces.ChartFragmentInteractor;
import com.usher.usher.interfaces.ChartFragmentView;
import com.usher.usher.views.ChartFragment;
import com.usher.usher.views.SessionStatisticsActivity;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChartFragmentPresenterImpl implements ChartFragmantPresenter {

    private ChartFragmentView view;
    private ChartFragmentInteractor interactor;
    //private LineChart mLineChart;

    public ChartFragmentPresenterImpl(ChartFragment view) {
        this.view = view;

        interactor = new ChartFragmentInteractorImpl(this);
    }


    @Override
    public void getChart(String method, String username, String session, SessionStatisticsActivity sessionStatistics) {
        if(method.equals("bars")) {
            interactor.getSessionData(username,session, sessionStatistics);
        }else if(method.equals("pie")) {

        //ACA SE CARGARIA EL RECYCLERVIEW

        }
    }


    @Override
    public void onReceiveStatisticsSuccessfull(JSONObject response) throws JSONException {
        if (view != null) {
            List<Entry> entries = new ArrayList<>();
            int presents;
            int minutes;
            for (int i = 0; i < response.length() - 1; i++) {
                presents = response.getJSONObject(Integer.toString(i)).getInt("presents");
                minutes = response.getJSONObject(Integer.toString(i)).getInt("minutes");
                entries.add(new BarEntry(minutes, presents));
            }
            LineDataSet lineDataSet = new LineDataSet(entries, "Representación cada 5 minutos");
            lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            lineDataSet.setValueTextSize(15f);
            lineDataSet.setColor(ColorTemplate.getHoloBlue());
            lineDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
            lineDataSet.setLineWidth(1.5f);
            lineDataSet.setDrawCircles(true);
            lineDataSet.setDrawValues(false);
            lineDataSet.setFillAlpha(65);
            lineDataSet.setFillColor(ColorTemplate.getHoloBlue());
            lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
            lineDataSet.setDrawCircleHole(false);

            LineData lineData = new LineData(lineDataSet);
            lineData.setValueTextColor(Color.GRAY);
            lineData.setValueTextSize(9f);

            Description description = new Description();
            description.setText("Cantidad de Presentes");
            description.setTextSize(15f);

            view.showChartView(lineData, description);
        }
    }

    @Override
    public void onReceiveStatisticsFailed() {
        if (view != null) {
            view.showChartFailed();
        }
    }

    @Override
    public void showErrorPresenter(JSONException error) {
        if (view != null) {
            error.printStackTrace();
            view.showError();
        }
    }
}
