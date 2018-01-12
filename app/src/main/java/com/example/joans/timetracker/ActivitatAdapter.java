package com.example.joans.timetracker;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ARB on 11/01/2018. asd
 */

public class ActivitatAdapter extends ArrayAdapter<PackDadesActivitatPosition> {

    public ActivitatAdapter(Context context, ArrayList<PackDadesActivitatPosition> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final PackDadesActivitatPosition data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_activity, parent, false);
        }
        // Lookup view for data population
        final TextView nom = convertView.findViewById(R.id.textview_tasques);
        TextView durada = convertView.findViewById(R.id.textview_tasques_sub);
        // Populate the data into the template view using the data object
        nom.setText(data.getDades().getNom());
        durada.setText(String.format("%02dh %02dm %02ds",
                data.getDades().getHores(), data.getDades().getMinuts(), data.getDades().getSegons()));

        final Button button = convertView.findViewById(R.id.buttonStart);
        final ImageView icon = convertView.findViewById(R.id.imageView);

        if (data.getDades().isProjecte()) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setVisibility(View.VISIBLE);
        }

        if (data.getDades().isProjecte()) {
            icon.setImageResource(R.drawable.projecte);
        } else {
            icon.setImageResource(R.drawable.tasca);
        }

        if (data.getDades().isCronometreEngegat()) {
            nom.setTextColor(Color.RED);
            button.setText(R.string.tasca_stop_button);
        } else {
            nom.setTextColor(Color.BLACK);
            button.setText(R.string.tasca_start_button);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!data.getDades().isCronometreEngegat()) {
                    intent = new Intent(
                            LlistaActivitatsActivity.ENGEGA_CRONOMETRE);
                    nom.setTextColor(Color.RED);
                    button.setText(R.string.tasca_stop_button);
                } else {
                    intent = new Intent(
                            LlistaActivitatsActivity.PARA_CRONOMETRE);
                    nom.setTextColor(Color.BLACK);
                    button.setText(R.string.tasca_start_button);
                }
                intent.putExtra("posicio", data.getPos());
                getContext().sendBroadcast(intent);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
