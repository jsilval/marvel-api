package com.jsilval.marvel.features.list.ui.marvellist;

import com.jsilval.marvel.core.Event;
import com.jsilval.marvel.core.data.source.MarvelDataSource;
import com.jsilval.marvel.core.data.source.remote.MarvelRepository;
import com.jsilval.marvel.features.list.model.Marvel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MarvelViewModel extends ViewModel {

    private final MutableLiveData<List<Marvel>> mItems = new MutableLiveData<>();

    private final MarvelRepository marvelRepository;

    private final MutableLiveData<Event<String>> mOpenDetailEvent = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isDataLoadingError = new MutableLiveData<>();

    public MarvelViewModel(MarvelRepository marvelRepository) {
        this.marvelRepository = marvelRepository;
    }

    public void start(String endpoint) {
        loadMarvel(endpoint);
    }

    private void updateList(List<Marvel> items) {
        mItems.setValue(items);
    }

    private void loadMarvel(String endpoint) {
        marvelRepository.getMarvels(endpoint, new MarvelDataSource.LoadMarvelsCallback() {
            @Override
            public void onMarvelsLoaded(List<Marvel> items) {
                isDataLoadingError.setValue(false);
                updateList(items);
            }

            @Override
            public void onDataNotAvailable() {
                isDataLoadingError.setValue(true);
            }
        });
    }

    public LiveData<List<Marvel>> getItems() {
        return mItems;
    }


    public void openDetail(String detailUrl) {
        mOpenDetailEvent.setValue(new Event<>(detailUrl));
    }

    public LiveData<Event<String>> getOpenDetailEvent() {
        return mOpenDetailEvent;
    }
}
