package com.jsilval.marvel.features.detail.ui.marveldetail;

import com.jsilval.marvel.core.data.source.MarvelDataSource;
import com.jsilval.marvel.core.data.source.remote.MarvelRepository;
import com.jsilval.marvel.features.detail.model.Detail;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MarvelDetailViewModel extends ViewModel {

    private final MutableLiveData<Boolean> isDataLoadingError = new MutableLiveData<>();
    private MutableLiveData<Detail> marvelDetail = new MutableLiveData<>();
    private MarvelRepository marvelRepository;

    public MarvelDetailViewModel(MarvelRepository marvelRepository) {
        this.marvelRepository = marvelRepository;
    }

    public void getDetail(String marvelId) {
        marvelRepository.getDetail(marvelId, new MarvelDataSource.LoadMarvelDetailCallback() {
            @Override
            public void onMarvelDetailLoaded(Detail detail) {
                isDataLoadingError.setValue(false);
                marvelDetail.setValue(detail);
            }

            @Override
            public void onDataNotAvailable() {
                isDataLoadingError.setValue(true);
            }
        });
    }

    public MutableLiveData<Detail> getMarvelDetail() {
        return marvelDetail;
    }

    public void start(String id) {
        if (id != null) {
            getDetail(id);
        }
    }
}
