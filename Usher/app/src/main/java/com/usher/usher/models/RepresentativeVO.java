package com.usher.usher.models;

import android.media.Image;

public class RepresentativeVO {

    private String name;
    private String textInformation;
    private String graphicPresentism;

    public RepresentativeVO(String name, String textInformation, String graphicPresentism) {
        this.name = name;
        this.textInformation = textInformation;
        this.graphicPresentism = graphicPresentism;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextInformation() {
        return textInformation;
    }

    public void setTextInformation(String textInformation) {
        this.textInformation = textInformation;
    }

    public String getGraphicPresentism() {
        return graphicPresentism;
    }

    public void setGraphicPresentism(String graphicPresentism) {
        this.graphicPresentism = graphicPresentism;
    }
}
