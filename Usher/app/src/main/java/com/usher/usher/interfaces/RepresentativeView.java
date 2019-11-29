package com.usher.usher.interfaces;

import com.usher.usher.models.RepresentativeVO;

import java.util.ArrayList;

public interface RepresentativeView {
    
    void showRepresentativeFailed();

    void showError();

    void showRepresentativeView(ArrayList<RepresentativeVO> listRepresentative);
}
