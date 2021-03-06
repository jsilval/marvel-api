package com.jsilval.marvel.core.data.source.remote;

import android.util.Log;

import com.jsilval.marvel.core.data.source.MarvelDataSource;
import com.jsilval.marvel.core.rest.MarvelClient;
import com.jsilval.marvel.core.rest.ServiceGenerator;
import com.jsilval.marvel.features.list.model.Marvel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.jsilval.marvel.core.rest.ServerConstans.API_KEY;
import static com.jsilval.marvel.core.rest.ServerConstans.HASH;
import static com.jsilval.marvel.core.rest.ServerConstans.TS;

public class MarvelRepository implements MarvelDataSource {

    private volatile static MarvelRepository INSTANCE = null;

    private MarvelRepository() {
    }

    public static MarvelRepository getInstance() {
        if (INSTANCE == null) {
            synchronized (MarvelRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MarvelRepository();
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getMarvels(String endpoint, @NonNull final LoadMarvelsCallback callback) {
        checkNotNull(callback);
        MarvelClient client = ServiceGenerator.createService(MarvelClient.class);
        final Call<List<Marvel>> marvel = client.getMarvel(endpoint, API_KEY, TS, HASH);

        marvel.enqueue(new Callback<List<Marvel>>() {
            @Override
            public void onResponse(@NonNull Call<List<Marvel>> call, @NonNull Response<List<Marvel>> response) {

            }

            @Override
            public void onFailure(@NonNull Call<List<Marvel>> call, @NonNull Throwable t) {

            }
        });

        // TODO PRUEBA
        List<Marvel> items = new ArrayList<>();
        items.add(new Marvel("Titulo", "url", "format", "img", "descripción"));
        callback.onMarvelsLoaded(items);
    }

    @Override
    public void getDetail(String marvelId, LoadMarvelDetailCallback callback) {
        Log.d(getClass().getSimpleName(), "getDetail: ");
    }
}
