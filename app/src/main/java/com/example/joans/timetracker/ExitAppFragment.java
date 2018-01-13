package com.example.joans.timetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class ExitAppFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.warning)
               .setMessage(R.string.warning_exit_app)
               .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dismiss();
                       getActivity().sendBroadcast(new Intent(LlistaActivitatsActivity.DEMANAR_PARA_SERVEI));
                   }
               })
               .setNegativeButton(R.string.formulari_boto_tornar_enrere, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                   }
               });

        return builder.create();
    }
}
