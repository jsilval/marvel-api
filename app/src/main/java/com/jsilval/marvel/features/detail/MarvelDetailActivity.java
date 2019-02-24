package com.jsilval.marvel.features.detail;

import android.os.Bundle;

import com.jsilval.marvel.R;
import com.jsilval.marvel.core.ViewModelFactory;
import com.jsilval.marvel.core.data.source.remote.MarvelRepository;
import com.jsilval.marvel.core.utilities.ActivityUtils;
import com.jsilval.marvel.features.detail.ui.marveldetail.MarvelDetailFragment;
import com.jsilval.marvel.features.detail.ui.marveldetail.MarvelDetailViewModel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class MarvelDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MARVEL_ID = "MARVEL_ID";

    private MarvelDetailViewModel marvelDetailViewModel;

    public static MarvelDetailViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(MarvelRepository.getInstance());
        return ViewModelProviders.of(activity, factory).get(MarvelDetailViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvel_detail_activity);

        setupToolbar();

        setupViewFragment();

        marvelDetailViewModel = obtainViewModel(this);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowHomeEnabled(true);
    }

    private void setupViewFragment() {
        String marvelId = getIntent().getStringExtra(EXTRA_MARVEL_ID);

        MarvelDetailFragment marvelFragment =
                (MarvelDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (marvelFragment == null) {
            marvelFragment = MarvelDetailFragment.newInstance(marvelId);
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), marvelFragment, R.id.contentFrame);
        }
    }
}
