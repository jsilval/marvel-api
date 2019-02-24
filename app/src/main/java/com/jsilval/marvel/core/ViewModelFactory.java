package com.jsilval.marvel.core;

import android.annotation.SuppressLint;

import com.jsilval.marvel.core.data.source.remote.MarvelRepository;
import com.jsilval.marvel.features.detail.ui.marveldetail.MarvelDetailViewModel;
import com.jsilval.marvel.features.list.ui.marvellist.MarvelViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final MarvelRepository marvelRepository;

    private ViewModelFactory(MarvelRepository repository) {
        marvelRepository = repository;
    }

    public static ViewModelFactory getInstance(MarvelRepository repository) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(repository);
                }
            }
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MarvelViewModel.class)) {
            return (T) new MarvelViewModel(marvelRepository);
        } else if (modelClass.isAssignableFrom(MarvelDetailViewModel.class)) {
            return (T) new MarvelDetailViewModel(marvelRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
