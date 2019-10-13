package com.usher.usher.services;

import com.usher.usher.BlockStatistics;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface StatisticsInterface {

    //@GET("Historico.php")
    @GET("usher-api/index.php")
    //@GET("usher-api/block_hist?token=48370255gBrgdlpl050588")
    //Call<List<BlockStatistics>> getStatisticsInfo(@Header("token") String token, @Query("username") String username, @Query("session") String session);
    //Call<List<BlockStatistics>> getStatisticsInfo(@QueryMap Map<String, String> params);
    Call<List<BlockStatistics>> getStatisticsInfo();
}
