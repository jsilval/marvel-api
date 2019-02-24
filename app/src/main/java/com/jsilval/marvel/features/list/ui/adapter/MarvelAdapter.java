package com.jsilval.marvel.features.list.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jsilval.marvel.R;
import com.jsilval.marvel.databinding.MarvelItemBinding;
import com.jsilval.marvel.features.list.model.Marvel;
import com.jsilval.marvel.features.list.ui.marvellist.MarvelViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.ViewHolder> {

    private final MarvelViewModel marvelViewModel;

    private List<Marvel> mItems;

    private LifecycleOwner lifecycleOwner;

    private LayoutInflater layoutInflater;

    public MarvelAdapter(List<Marvel> items, MarvelViewModel marvelViewModel,
                         LifecycleOwner activity) {
        this.marvelViewModel = marvelViewModel;
        setList(items);
        lifecycleOwner = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        MarvelItemBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.marvel_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        MarvelAdapterListener userClickListener = new MarvelAdapterListener() {
            @Override
            public void onMarvelItemClicked(Marvel marvel) {
                Log.d(getClass().getSimpleName(), "onMarvelItemClicked: ");
                marvelViewModel.openDetail(marvel.getDetailUrl());
            }
        };

        holder.binding.setMarvel(mItems.get(position));
        holder.binding.setLifecycleOwner(lifecycleOwner);

        holder.binding.setListener(userClickListener);

        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    private void setList(List<Marvel> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public void replaceData(List<Marvel> items) {
        setList(items);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final MarvelItemBinding binding;

        public ViewHolder(MarvelItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
