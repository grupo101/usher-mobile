package com.usher.usher.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;
import com.usher.usher.R;
import com.usher.usher.interfaces.ChartFragmantPresenter;
import com.usher.usher.interfaces.ChartFragmentView;
import com.usher.usher.presenters.ChartFragmentPresenterImpl;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartFragment extends Fragment implements ChartFragmentView {

    private LineChart mLineChart;
    private ChartFragmantPresenter presenter;
    private SessionStatisticsActivity sessionStatistics;


    public ChartFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        mLineChart = view.findViewById(R.id.barChart);
        sessionStatistics = (SessionStatisticsActivity) getActivity();

        presenter = new ChartFragmentPresenterImpl(this);

        presenter.getChart(getArguments().getString("username"),
                  getArguments().getString("session"),
                  sessionStatistics);

        return view;
    }

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
        Toast.makeText(getContext(), "No existen estadisticas para la sesion seleccionada", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
    }

}
