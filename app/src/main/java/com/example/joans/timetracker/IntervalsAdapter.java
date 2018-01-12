package com.example.joans.timetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiacheny on 2018/1/11.
 */

public class IntervalsAdapter extends ArrayAdapter<DadesInterval> {

    public IntervalsAdapter(Context context, List<DadesInterval> data) {
        super(context, 0, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final DadesInterval di = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_interval, parent, false);
        }

        TextView descripcio = (TextView) convertView.findViewById(R.id.textview_intervals);
        SimpleDateFormat formatter = new SimpleDateFormat();
        String interval_data_inici = convertView.getResources().getString(R.string.interval_data_inici);
        String interval_data_fi = convertView.getResources().getString(R.string.interval_data_fi);
        String durada = convertView.getResources().getString(R.string.durada);
        descripcio.setText(
                interval_data_inici + " " + formatter.format(di.getDataInicial()) + "\n"
                + interval_data_fi + " " + formatter.format(di.getDataFinal()) + "\n"
                + durada + " " + di.getDuradaFormatted()
        );

        return convertView;
    }
}
