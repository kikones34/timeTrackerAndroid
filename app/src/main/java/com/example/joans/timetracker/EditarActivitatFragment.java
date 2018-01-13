package com.example.joans.timetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class EditarActivitatFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        final String nom = getArguments().getString("nom");
        final String descripcio = getArguments().getString("descripcio");
        final int id = getArguments().getInt("id");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_editar_activitat, null);
        builder.setView(view);

        final TextView nom_input = view.findViewById(R.id.edit_nom);
        final TextView descripcio_input = view.findViewById(R.id.edit_descripcio);
        nom_input.setText(nom);
        descripcio_input.setText(descripcio);

        builder.setTitle(R.string.edit_dialog).setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nom_input.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText(getActivity(), R.string.nom_buit, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (descripcio_input.getText().toString().isEmpty()) {
                        Toast toast = Toast.makeText(getActivity(), R.string.descripcio_buida, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Intent inte = new Intent(LlistaActivitatsActivity.ACTIVITAT_EDITAT);
                        inte.putExtra("nom", nom_input.getText().toString());
                        inte.putExtra("descripcio", descripcio_input.getText().toString());
                        inte.putExtra("id", id);
                        getActivity().sendBroadcast(inte);
                    }
                }
            }
        });

        builder.setNegativeButton(R.string.formulari_boto_tornar_enrere, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });

    return builder.create();

    }
}
