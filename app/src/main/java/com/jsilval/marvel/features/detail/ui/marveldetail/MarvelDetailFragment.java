package com.jsilval.marvel.features.detail.ui.marveldetail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsilval.marvel.databinding.MarvelDetailFragmentBinding;
import com.jsilval.marvel.features.detail.MarvelDetailActivity;
import com.jsilval.marvel.features.detail.model.Detail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

public class MarvelDetailFragment extends Fragment {
    public static final String ARGUMENT_MARVEL_ID = "MARVEL_ID";

    private MarvelDetailViewModel mViewModel;
    private MarvelDetailFragmentBinding fragmentBinding;

    public static MarvelDetailFragment newInstance(String marvelId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_MARVEL_ID, marvelId);
        MarvelDetailFragment fragment = new MarvelDetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.start(getArguments().getString(ARGUMENT_MARVEL_ID));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentBinding = MarvelDetailFragmentBinding.inflate(inflater, container, false);

        mViewModel = MarvelDetailActivity.obtainViewModel(getActivity());

        fragmentBinding.setViewmodel(mViewModel);
        fragmentBinding.setLifecycleOwner(getActivity());

        mViewModel.getMarvelDetail().observe(this, new Observer<Detail>() {
            @Override
            public void onChanged(Detail detail) {
                Log.d(getClass().getSimpleName(), "onChanged: ");
            }
        });

        return fragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
