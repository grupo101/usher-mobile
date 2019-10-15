package com.usher.usher.views;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.usher.usher.R;
import com.usher.usher.interfaces.ChartFragmantPresenter;
import com.usher.usher.interfaces.ChartFragmentView;
import com.usher.usher.presenters.ChartFragmentPresenterImpl;
import com.usher.usher.requests.BlockStatisticsRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment implements ChartFragmentView {

    private LineChart mLineChart;
    private RecyclerView mRecyceViewPieChart;
    public ChartFragment() {
        // Required empty public constructor
    }
    private ChartFragmantPresenter presenter;
    private SessionStatistics sessionStatistics;
    private Response.Listener<String> responseListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        mLineChart = view.findViewById(R.id.barChart);
        mRecyceViewPieChart = view.findViewById(R.id.pieChart);
        sessionStatistics = (SessionStatistics) getActivity();

        presenter = new ChartFragmentPresenterImpl(this);

        presenter.getChart(getArguments().getString("method"), getArguments().getString("username"), getArguments().getString("session"), sessionStatistics);

        //getChart(getArguments().getString("method"), getArguments().getString("username"), getArguments().getString("session"));
        return view;
    }

    /*private void getChart(final String method, String username, String session){
        if(method.equals("bars")) {
            responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean succes = jsonResponse.getBoolean("succes");

                        if (succes) {
                            List<Entry> entries = new ArrayList<>();
                            int presents;
                            int minutes;
                            for (int i = 0; i < jsonResponse.length() - 1; i++) {
                                presents = jsonResponse.getJSONObject(Integer.toString(i)).getInt("presents");
                                minutes = jsonResponse.getJSONObject(Integer.toString(i)).getInt("minutes");
                                entries.add(new BarEntry(minutes, presents));
                            }
                            LineDataSet lineDataSet = new LineDataSet(entries, "Representación cada 5 minutos");
                            lineDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                            lineDataSet.setValueTextSize(15f);
                            lineDataSet.setColor(ColorTemplate.getHoloBlue());
                            lineDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
                            lineDataSet.setLineWidth(1.5f);
                            lineDataSet.setDrawCircles(false);
                            lineDataSet.setDrawValues(false);
                            lineDataSet.setFillAlpha(65);
                            lineDataSet.setFillColor(ColorTemplate.getHoloBlue());
                            lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
                            lineDataSet.setDrawCircleHole(false);

                            LineData lineData = new LineData(lineDataSet);
                            lineData.setValueTextColor(Color.GRAY);
                            lineData.setValueTextSize(9f);

                            mLineChart.setVisibility(View.VISIBLE);
                            mLineChart.animateY(3000);
                            mLineChart.setData(lineData);

                            Description description = new Description();
                            description.setText("Cantidad de Presentes");
                            description.setTextSize(15f);
                            mLineChart.setDescription(description);
                            mLineChart.invalidate();
                        }
                        else
                            Toast.makeText( getContext(), "No existen estadisticas para la sesion seleccionada", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        Toast.makeText( getContext(), "Error", Toast.LENGTH_LONG).show();
                        //presenter.showErrorPresenter(e);
                    }
                }
            };
            BlockStatisticsRequest blockStatisticsRequest = new BlockStatisticsRequest(username, session, responseListener);
            RequestQueue queue = Volley.newRequestQueue(sessionStatistics);
            queue.add(blockStatisticsRequest);
        }else if(method.equals("pie")) {

            //ACA SE CARGARIA EL RECYCLERVIEW

        }
    }*/

    @Override
    public void showChartView(LineData lineData, Description description) {
        mLineChart.setVisibility(View.VISIBLE);
        mLineChart.animateY(3000);
        mLineChart.setData(lineData);
        mLineChart.setDescription(description);
        mLineChart.invalidate();
    }

    @Override
    public void showChartFailed() {
        Toast.makeText( getContext(), "No existen estadisticas para la sesion seleccionada", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText( getContext(), "Error", Toast.LENGTH_LONG).show();
    }

}
