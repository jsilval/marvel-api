package com.jsilval.marvel.core.data.source;

import com.jsilval.marvel.features.detail.model.Detail;
import com.jsilval.marvel.features.list.model.Marvel;

import java.util.List;

import androidx.annotation.NonNull;

public interface MarvelDataSource {

    void getMarvels(String endpoint, @NonNull LoadMarvelsCallback callback);

    void getDetail(String marvelId, LoadMarvelDetailCallback callback);

    interface LoadMarvelsCallback {

        void onMarvelsLoaded(List<Marvel> marvels);

        void onDataNotAvailable();

    }

    interface LoadMarvelDetailCallback {

        void onMarvelDetailLoaded(Detail detail);

        void onDataNotAvailable();

    }
}
