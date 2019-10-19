package com.usher.usher.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.usher.usher.R;

public class RepresentativeFragment extends Fragment {

    //private TextView mMesajeTest;

    @Override
    public View onCreateView(LayoutInflater inflater, android.view.ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart_representative, container, false);
        //mMesajeTest = view.findViewById(R.id.messaje_test);
        return view;
    }
}
