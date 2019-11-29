package com.usher.usher.interfaces;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.LineData;

public interface ChartFragmentView {

    void showChartFailed();

    void showError();

    void showChartView(LineData lineData, Description description);
}
