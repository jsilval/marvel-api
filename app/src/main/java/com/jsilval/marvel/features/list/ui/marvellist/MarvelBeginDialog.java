package com.jsilval.marvel.features.list.ui.marvellist;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jsilval.marvel.R;
import com.jsilval.marvel.core.utilities.StringUtils;
import com.jsilval.marvel.features.list.ui.MarvelActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static com.jsilval.marvel.core.rest.ServerConstans.CHARACTERS;
import static com.jsilval.marvel.core.rest.ServerConstans.COMICS;
import static com.jsilval.marvel.core.rest.ServerConstans.CREATORS;
import static com.jsilval.marvel.core.rest.ServerConstans.EVENTS;
import static com.jsilval.marvel.core.rest.ServerConstans.SERIES;
import static com.jsilval.marvel.core.rest.ServerConstans.STORIES;

public class MarvelBeginDialog extends DialogFragment {

    private TextInputLayout tiLayout;

    private TextInputEditText etValidation;

    private View rootView;

    private MarvelActivity activity;

    public static MarvelBeginDialog newInstance(MarvelActivity activity) {
        MarvelBeginDialog dialog = new MarvelBeginDialog();
        dialog.activity = activity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("Validación")
                .setCancelable(false)
                .setPositiveButton("Validar", null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> {
            onDialogShow(alertDialog);
        });
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.marvel_begin_dialog, null, false);

        tiLayout = rootView.findViewById(R.id.tiLayout);
        etValidation = rootView.findViewById(R.id.etValidation);
    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onDoneClicked();
        });
    }

    private void onDoneClicked() {
        final String operation = etValidation.getText().toString();
        if (StringUtils.isValidString(operation)) {
            try {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                final double result = (double) engine.eval(operation);

                String endpoint;
                if (result == 0) {
                    endpoint = CHARACTERS;
                } else if (result % 3 == 0) {
                    endpoint = COMICS;
                } else if (result % 5 == 0) {
                    endpoint = COMICS;
                } else if (result % 7 == 0) {
                    endpoint = CREATORS;
                } else if (result % 11 == 0) {
                    endpoint = EVENTS;
                } else if (result % 13 == 0) {
                    endpoint = SERIES;
                } else {
                    endpoint = STORIES;
                }

                activity.setupViewFragment(endpoint);
                dismiss();
                tiLayout.setError("Correcto");
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        } else {
            tiLayout.setError("Error");
        }
    }
}
