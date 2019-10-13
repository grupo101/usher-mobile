package com.usher.usher.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.usher.usher.ChartFragment;
import com.usher.usher.R;
import com.usher.usher.interfaces.SessionStatisticsPresenter;
import com.usher.usher.interfaces.SessionStatisticsView;
import com.usher.usher.presenters.SessionStatisticsPresenterImpl;

import java.util.ArrayList;

public class SessionStatistics extends AppCompatActivity implements SessionStatisticsView {

    private Spinner spinner;
    private Button btn_lineChart, btn_pieChart;
    private ProgressBar pr_progressSession;
    private SessionStatisticsPresenter presenter;
    private String username, method;
    ChartFragment chartFragment;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_statistics);

        spinner = findViewById(R.id.spinner);
        btn_lineChart = findViewById(R.id.btn_updateLine);
        btn_pieChart = findViewById(R.id.btn_updatePie);
        pr_progressSession = findViewById(R.id.progressSession);

        final Intent intent = getIntent();
        username = intent.getStringExtra("username");
        method  = getIntent().getStringExtra("method");
        chartFragment = new ChartFragment();
        bundle = new Bundle();
        bundle.putString("method",method);
        bundle.putString("username",username);
        bundle.putString("session","15");

        chartFragment.setArguments(bundle);


        presenter = new SessionStatisticsPresenterImpl(this);

        presenter.fillSpinner(username);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,chartFragment).commit();


        btn_lineChart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                method = "bars";
                //spinString = spinner.getSelectedItem().toString()
                //String[] session  = spinString.split(".");

                //presenter.updateFragment(method, username, parts[0]);
            }
        });

        btn_pieChart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                method = "pie";
                //spinString = spinner.getSelectedItem().toString()
                //String[] session  = spinString.split(".");

                //presenter.updateFragment(method, username, parts[0]);
            }
        });

        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home)
            this.finish();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress(boolean option) {
        pr_progressSession.setVisibility(option ? View.VISIBLE : View.GONE);
    }


    @Override
    public void loadList (ArrayList arrayList){
        ArrayAdapter<String> list_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayList);
        list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(list_adapter);
        spinner.setSelection(0);

    }
}
