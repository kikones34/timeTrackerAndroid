package com.example.joans.timetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Jiacheny on 2018/1/10.
 */

public class OpcionsDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Integer id = getArguments().getInt("id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.opcions_dialog)
                .setItems(R.array.opcions_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Intent inte = new Intent(LlistaActivitatsActivity.MES_DETALLS);
                                inte.putExtra("id", id);
                                getActivity().sendBroadcast(inte);
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                        }
                    }
                });
        return builder.create();
    }
}
