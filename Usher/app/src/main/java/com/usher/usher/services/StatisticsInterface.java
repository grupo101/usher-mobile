package com.usher.usher.services;

import com.usher.usher.BlockStatistics;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StatisticsInterface {

    @GET("Historico.php")
    Call<List<BlockStatistics>> getStatisticsInfo();
}
