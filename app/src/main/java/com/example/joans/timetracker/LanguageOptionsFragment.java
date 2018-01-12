package com.example.joans.timetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Locale;

/**
 * Created by ARB on 12/01/2018.
 */

public class LanguageOptionsFragment extends DialogFragment {

    private void changeLocale(Locale locale)
    {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        getActivity().recreate();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.title_options_language)
                .setItems(R.array.options_language, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Locale[] locales = {
                                new Locale("ca"),
                                new Locale("es"),
                                new Locale("en"),
                                new Locale("zh"),
                        };
                        changeLocale(locales[which]);
                        dismiss();
                    }
                });
        return builder.create();
    }
}
