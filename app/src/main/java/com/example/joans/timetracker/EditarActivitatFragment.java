package com.example.joans.timetracker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by Jiacheny on 2018/1/11.
 */

public class EditarActivitatFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        final String nom = getArguments().getString("nom");
        final String descripcio = getArguments().getString("descripcio");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.fragment_editar_activitat, null));

        final TextView nom_input = getView().findViewById(R.id.edit_nom);
        final TextView descripcio_input = getView().findViewById(R.id.edit_descripcio);
        nom_input.setText(nom);
        descripcio_input.setText(descripcio);

        builder.setTitle(R.string.edit_dialog).setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (nom.isEmpty()) {
                    Toast toast = Toast.makeText(getActivity(), R.string.nom_buit, Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    if (descripcio.isEmpty()) {
                        Toast toast = Toast.makeText(getActivity(), R.string.descripcio_buida, Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Intent inte = new Intent(LlistaActivitatsActivity.ACTIVITAT_EDITAT);
                        inte.putExtra("nom", nom_input.getText());
                        inte.putExtra("descripcio", descripcio_input.getText());
                        getActivity().sendBroadcast(inte);
                    }
                }
            }
        });

    return builder.create();

    }
}
