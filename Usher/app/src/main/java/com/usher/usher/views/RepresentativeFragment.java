package com.usher.usher.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.usher.usher.R;
import com.usher.usher.interfaces.RepresentativePresenter;
import com.usher.usher.interfaces.RepresentativeView;
import com.usher.usher.models.RepresentativeVO;
import com.usher.usher.presenters.RepresentativePresenterImpl;

import java.util.ArrayList;

public class RepresentativeFragment extends Fragment implements RepresentativeView {


    public RepresentativeFragment( ) {
    }

    //Comienzo mi RecyclerView
    ArrayList<RepresentativeVO> listRepresentative;
    RecyclerView recyclerRepresentative;
    private RepresentativePresenter presenter;
    private SessionStatisticsActivity sessionStatistics;


    @Override
    public View onCreateView(LayoutInflater inflater, android.view.ViewGroup container,
                             Bundle savedInstanceState) {
        listRepresentative = new ArrayList<>();
        sessionStatistics = (SessionStatisticsActivity) getActivity();

        View view = inflater.inflate(R.layout.fragment_chart_representative, container, false);

        recyclerRepresentative = view.findViewById(R.id.vRepresentativeRecyclerView);
        recyclerRepresentative.setLayoutManager(new LinearLayoutManager(getActivity()));

        presenter = new RepresentativePresenterImpl(this);

        presenter.loadRepresentative(getArguments().getString("username"), getArguments().getString("session"),
                sessionStatistics);

        return view;
    }

    @Override
    public void showRepresentativeView(ArrayList<RepresentativeVO> listRepresentative) {
        //Union entre los datos del interactor cleneados por el presenter en la view
        this.listRepresentative = listRepresentative;
        RepresentativeAdapter adapter = new RepresentativeAdapter(listRepresentative, sessionStatistics);
        recyclerRepresentative.setAdapter(adapter);
    }

    @Override
    public void showRepresentativeFailed() {
        Toast.makeText(getContext(), "No existen datos de los representantes", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
    }

}
