package com.jsilval.marvel.features.list.ui;

import android.content.Intent;
import android.os.Bundle;

import com.jsilval.marvel.R;
import com.jsilval.marvel.core.Event;
import com.jsilval.marvel.core.ViewModelFactory;
import com.jsilval.marvel.core.data.source.remote.MarvelRepository;
import com.jsilval.marvel.core.utilities.ActivityUtils;
import com.jsilval.marvel.features.detail.MarvelDetailActivity;
import com.jsilval.marvel.features.list.ui.marvellist.MarvelBeginDialog;
import com.jsilval.marvel.features.list.ui.marvellist.MarvelFragment;
import com.jsilval.marvel.features.list.ui.marvellist.MarvelViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MarvelActivity extends AppCompatActivity implements MarvelItemNavigator {

    private static final String MARVEL_BEGIN_DIALOG_TAG = "marvel_dialog_tag";

    private MarvelViewModel marvelViewModel;

    public static MarvelViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(MarvelRepository.getInstance());
        return ViewModelProviders.of(activity, factory).get(MarvelViewModel.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marvel_activity);

        setupToolbar();

//        setupViewFragment();

        marvelViewModel = obtainViewModel(this);

        marvelViewModel.getOpenDetailEvent().observe(this, new Observer<Event<String>>() {
            @Override
            public void onChanged(Event<String> marvelEvent) {
                String marvelId = marvelEvent.getContentIfNotHandled();
                if (marvelId != null) {
                    openMarvelDetails(marvelId);
                }
            }
        });

        promptForStringValidation();
    }

    @Override
    public void openMarvelDetails(String marvelId) {
        Intent intent = new Intent(this, MarvelDetailActivity.class);
        intent.putExtra(MarvelDetailActivity.EXTRA_MARVEL_ID, marvelId);
        startActivity(intent);
    }

    public void setupViewFragment(String endpoint) {
        MarvelFragment marvelFragment =
                (MarvelFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (marvelFragment == null) {
            marvelFragment = MarvelFragment.newInstance(endpoint);
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), marvelFragment, R.id.contentFrame);
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void promptForStringValidation() {
        MarvelBeginDialog dialog = MarvelBeginDialog.newInstance(this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), MARVEL_BEGIN_DIALOG_TAG);
    }
}
