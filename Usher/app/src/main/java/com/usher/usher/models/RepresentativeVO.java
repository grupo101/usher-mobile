package com.usher.usher.models;

public class RepresentativeVO {

    private String photo;
    private String nameAndSurname;
    private String block;
    private Float presentism;
    private Float ausentism;

    public RepresentativeVO(String photo, String nameAndSurname, String block, Integer presence, Integer total) {
        this.photo = photo;
        this.nameAndSurname = nameAndSurname;
        this.block = block;
        this.presentism = total != 0 ? (float) presence / (float) total : (float) 0;
        this.ausentism = total != 0 ? (float) (total - presence) / (float) total : (float) 0;
    }

    public String getNameAndSurname() {
        return nameAndSurname;
    }

    public void setNameAndSurname(String nameAndSurname) {
        this.nameAndSurname = nameAndSurname;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public Float getPresentism() {
        return presentism;
    }

    public void setPresentism(Float presentism) {
        this.presentism = presentism;
    }

    public Float getAusentism() {
        return ausentism;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
