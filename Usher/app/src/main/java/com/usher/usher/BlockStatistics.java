package com.usher.usher;

import com.google.gson.annotations.SerializedName;

public class BlockStatistics {

    @SerializedName("id")
    private int time;

    @SerializedName("status")
    private String total;

    public int getTime() {
        return time;
    }

    public int getTotal() {
        int i, sum = 0;
        for (i = 0; i<total.length(); i++) {
        //preguntar si i es una de las bancas del bloque del usuario
            if (total.charAt(i) == '0')
                sum++;
        }
        return sum;
    }
}
