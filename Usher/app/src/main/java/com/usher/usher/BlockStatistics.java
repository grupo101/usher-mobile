package com.usher.usher;

import com.google.gson.annotations.SerializedName;

public class BlockStatistics {

    @SerializedName("minutes")
    private int time;

    @SerializedName("presents")
    private int presentes;

    public int getTime() {
        return time;
    }

    public int getTotal() {
        return presentes;
    }
}
