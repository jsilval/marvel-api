package com.jsilval.marvel.features.list.ui.marvellist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jsilval.marvel.databinding.MarvelFragmentBinding;
import com.jsilval.marvel.features.list.model.Marvel;
import com.jsilval.marvel.features.list.ui.MarvelActivity;
import com.jsilval.marvel.features.list.ui.adapter.MarvelAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MarvelFragment extends Fragment {

    private static final String ARGUMENT_MARVEL_END_POINT = "marvel_end_point";

    private MarvelViewModel marvelViewModel;

    private MarvelFragmentBinding marvelFragmentBinding;

    private MarvelAdapter listAdapter;

    public MarvelFragment() {
    }

    public static MarvelFragment newInstance(String endpoint) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_MARVEL_END_POINT, endpoint);
        MarvelFragment fragment = new MarvelFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        marvelViewModel.start(getArguments().getString(ARGUMENT_MARVEL_END_POINT));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        marvelFragmentBinding = MarvelFragmentBinding.inflate(inflater, container, false);

        marvelViewModel = MarvelActivity.obtainViewModel(getActivity());

        marvelFragmentBinding.setViewmodel(marvelViewModel);
        marvelFragmentBinding.setLifecycleOwner(getActivity());

        marvelViewModel.getItems().observe(this, new Observer<List<Marvel>>() {
            @Override
            public void onChanged(List<Marvel> marvels) {
                Log.d(getClass().getSimpleName(), "onChanged: ");
                listAdapter.replaceData(marvels);
            }
        });

        return marvelFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupListAdapter();
    }

    private void setupListAdapter() {
        RecyclerView rvList = marvelFragmentBinding.rvList;

        listAdapter = new MarvelAdapter(
                new ArrayList<Marvel>(0),
                marvelViewModel,
                getActivity()
        );

        rvList.setAdapter(listAdapter);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
