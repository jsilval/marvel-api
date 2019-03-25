package com.jsilval.marvel.core.rest;

import com.jsilval.marvel.features.list.model.Marvel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MarvelClient {

    @GET
    Call<List<Marvel>> getMarvel(@Url String url,
                                 @Query("apikey") String apikey,
                                 @Query("ts") String ts,
                                 @Query("hash") String hash);

}
