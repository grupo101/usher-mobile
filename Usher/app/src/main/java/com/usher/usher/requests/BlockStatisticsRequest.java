package com.usher.usher.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlockStatisticsRequest {

    private static final String BASE_URL = "http:/192.168.1.101:8080/";
    //private static final String BASE_URL = "http:/10.20.56.72:8080/";
    private static Retrofit retrofit;

    public static Retrofit getApiClient(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                    addConverterFactory(GsonConverterFactory.create()).build();

        }

        return retrofit;
    }
}