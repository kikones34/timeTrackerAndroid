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

public class ActivityAdapter extends ArrayAdapter<PackDadesActivitatPosition> {

    public ActivityAdapter(Context context, ArrayList<PackDadesActivitatPosition> data) {
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
        TextView nom = (TextView) convertView.findViewById(R.id.textview_tasques);
        TextView durada = (TextView) convertView.findViewById(R.id.textview_tasques_sub);
        // Populate the data into the template view using the data object
        nom.setText(data.getDades().getNom());
        durada.setText(String.format("%02dh %02dm %02ds",
                data.getDades().getHores(), data.getDades().getMinuts(), data.getDades().getSegons()));

        final Button button = convertView.findViewById(R.id.buttonStart);
        final ImageView imageView = convertView.findViewById(R.id.imageView);

        if (data.getDades().isProjecte()) {
            button.setVisibility(View.INVISIBLE);
        } else {
            button.setVisibility(View.VISIBLE);
        }

        if (data.getDades().isProjecte()) {
            imageView.setImageResource(R.drawable.projecte);
        } else {
            imageView.setImageResource(R.drawable.tasca);
        }

        if (data.getDades().isCronometreEngegat()) {
            nom.setTextColor(Color.RED);
        } else {
            nom.setTextColor(Color.BLACK);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (!data.getDades().isCronometreEngegat()) {
                    intent = new Intent(
                            LlistaActivitatsActivity.ENGEGA_CRONOMETRE);
                    button.setText("Stop");
                } else {
                    intent = new Intent(
                            LlistaActivitatsActivity.PARA_CRONOMETRE);
                    button.setText("Start");
                }
                intent.putExtra("posicio", data.getPos());
                getContext().sendBroadcast(intent);
            }
        });

        // Return the completed view to render on screen
        return convertView;
    }
}
