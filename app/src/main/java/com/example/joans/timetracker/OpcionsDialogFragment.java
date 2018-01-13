package com.example.joans.timetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;


public class OpcionsDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Integer id = getArguments().getInt("id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.opcions_dialog)
                .setItems(R.array.opcions_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent[] intents = {
                                new Intent(LlistaActivitatsActivity.MES_DETALLS),
                                new Intent(LlistaActivitatsActivity.DEMANAR_EDITAR_ACTIVITAT),
                                new Intent(LlistaActivitatsActivity.DEMANAR_ELIMINAR_ACTIVITAT),
                        };
                        intents[which].putExtra("id", id);
                        getActivity().sendBroadcast(intents[which]);
                    }
                });
        return builder.create();
    }
}
