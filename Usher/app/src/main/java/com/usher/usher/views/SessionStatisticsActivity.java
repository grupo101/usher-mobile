package com.usher.usher.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.usher.usher.R;
import com.usher.usher.interfaces.SessionStatisticsPresenter;
import com.usher.usher.interfaces.SessionStatisticsView;
import com.usher.usher.presenters.SessionStatisticsPresenterImpl;

import java.util.ArrayList;

public class SessionStatisticsActivity extends AppCompatActivity implements SessionStatisticsView {

    private Spinner spinner;
    private SessionStatisticsPresenter presenter;
    private String username;
    private String[] session;
    ChartFragment chartFragment;
    RepresentativeFragment representativeFragment;
    Bundle bundle;
    FragmentTransaction fragmentTransaction;

    TabLayout tabLayout;
    ViewPager viewPager;
    SessionStatisticsViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_statistics);

        spinner = findViewById(R.id.spinner);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        viewPagerAdapter = new SessionStatisticsViewPagerAdapter(getSupportFragmentManager());
        representativeFragment = new RepresentativeFragment();
        chartFragment = new ChartFragment();

        final Intent intent = getIntent();
        username = intent.getStringExtra("username");

        bundle = new Bundle();
        bundle.putString("username", username);
        presenter = new SessionStatisticsPresenterImpl(this);
        presenter.fillSpinner(username);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView adapterView, View view, int i, long l) {
                //Fragment Chart
                session = spinner.getSelectedItem().toString().split("\\.");
                bundle.putString("session", session[0]);
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.detach(chartFragment).detach(representativeFragment);
                fragmentTransaction.attach(chartFragment).attach(representativeFragment);
                fragmentTransaction.commit();

                //Fragment RepresentativeVO
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
    public void loadTabLayoutFragments() {
        viewPagerAdapter.addFragments(chartFragment, getString(R.string.b_updateLine));
        viewPagerAdapter.addFragments(representativeFragment, getString(R.string.b_updatePie));
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void loadList(ArrayList arrayList) {
        ArrayAdapter<String> list_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayList);
        list_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(list_adapter);
        spinner.setSelection(0);
        session = spinner.getSelectedItem().toString().split("\\.");
        bundle.putString("session", session[0]);
        chartFragment.setArguments(bundle);
        representativeFragment.setArguments(bundle);
    }
}
