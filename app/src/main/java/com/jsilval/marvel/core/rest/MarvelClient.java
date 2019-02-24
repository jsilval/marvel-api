package com.jsilval.marvel.core.rest;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MarvelClient {

    @GET
    Call<JsonObject> getMarvel(@Url String url,
                               @Query("apikey") String apikey,
                               @Query("ts") String ts,
                               @Query("hash") String hash);

}
