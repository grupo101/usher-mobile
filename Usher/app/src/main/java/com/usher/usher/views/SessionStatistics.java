package com.usher.usher.views;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.usher.usher.R;
import com.usher.usher.interfaces.SessionStatisticsPresenter;
import com.usher.usher.interfaces.SessionStatisticsView;
import com.usher.usher.presenters.SessionStatisticsPresenterImpl;

import org.intellij.lang.annotations.JdkConstants;

import java.util.ArrayList;

public class SessionStatistics extends AppCompatActivity implements SessionStatisticsView {

    private Spinner spinner;
    private Button btn_lineChart, btn_pieChart;
    private ProgressBar pr_progressSession;
    private SessionStatisticsPresenter presenter;
    private String username, method, spinString;
    private String[] session;
    ChartFragment chartFragment;
    RepresentativeFragment representativeFragment;
    Bundle bundle;
    FragmentTransaction fragmentTransaction;

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_statistics);

        spinner = findViewById(R.id.spinner);
        btn_lineChart = findViewById(R.id.btn_updateLine);
        btn_pieChart = findViewById(R.id.btn_updatePie);
        pr_progressSession = findViewById(R.id.progressSession);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);


        final Intent intent = getIntent();
        username = intent.getStringExtra("username");
        method  = getIntent().getStringExtra("method");
        chartFragment = new ChartFragment();
        bundle = new Bundle();
        bundle.putString("method",method);
        bundle.putString("username",username);
        presenter = new SessionStatisticsPresenterImpl(this);
        presenter.fillSpinner(username);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //Debe refrescar ambos fragmentos

            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                //Fragment Chart
                bundle.putString("method","bars");
                session  = spinner.getSelectedItem().toString().split("\\.");
                bundle.putString("session",session[0]);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(chartFragment).detach(representativeFragment);
                fragmentTransaction.attach(chartFragment).attach(representativeFragment);
                fragmentTransaction.commit();

                //Fragment Representative
                //XXXXXX
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
    public void loadTabLayoutFragments() {
        //Inicio del Fragmento Chart
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

        //Inicio del Fragmento de Recycler
        //XXXXXX

        //Inicio de el paginador de ViewHolder
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //Aca va el de las estadisticas generales
        representativeFragment = new RepresentativeFragment();
        viewPagerAdapter.addFragments(chartFragment, getString(R.string.b_updateLine));
        //Aca iria el de los representantes
        viewPagerAdapter.addFragments(representativeFragment, getString(R.string.b_updatePie));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    public void loadList (ArrayList arrayList){
        ArrayAdapter<String> list_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayList);
        list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(list_adapter);
        spinner.setSelection(0);
        session  = spinner.getSelectedItem().toString().split("\\.");
        bundle.putString("session",session[0]);
        chartFragment.setArguments(bundle);
    }
}
