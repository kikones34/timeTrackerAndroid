package com.example.joans.timetracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jiacheny on 2018/1/9.
 */

public class tasquesAdapter extends ArrayAdapter<packDadesActivitatPosition> {
    ArrayList<packDadesActivitatPosition> mArrayList;
    private ListView listView;
    public tasquesAdapter(Context context, int layoutID, ArrayList<packDadesActivitatPosition> tasques, ListView listView) {
        super(context, layoutID, tasques);

        mArrayList = tasques;
        this.listView = listView;
    }

    static class ViewHolder {
        TextView text;
        Button btn;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        packDadesActivitatPosition pack = getItem(pos);

        View rowView = convertView;

        if (rowView == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            rowView = inflater.inflate(R.layout.temp_tasques_tv_and_btn, parent, false);
            ViewHolder h = new ViewHolder();
            h.text = (TextView) rowView.findViewById(R.id.textview_tasques);
            h.btn = (Button) rowView.findViewById(R.id.buttonStart);
            rowView.setTag(h);
        }

        ViewHolder h = (ViewHolder) rowView.getTag();

        h.text.setText(pack.getDades().toString());

        h.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(LlistaActivitatsActivity.BAIXA_NIVELL);
                inte.putExtra("posicio", mArrayList.get(pos).getPos());
                getContext().sendBroadcast(inte);
                if (mArrayList.get(pos).getDades().isTasca()) {
                    getContext().startActivity(new Intent(getContext(),
                            LlistaIntervalsActivity.class));
                    // en aquesta classe ja es demanara la llista de fills
                } else {
                    // no pot ser!
                    assert false : "activitat que no es projecte ni tasca";
                }
            }
        });

        h.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte;
                if (!mArrayList.get(pos).getDades().isCronometreEngegat()) {
                    inte = new Intent(
                            LlistaActivitatsActivity.ENGEGA_CRONOMETRE);
                } else {
                    inte = new Intent(
                            LlistaActivitatsActivity.PARA_CRONOMETRE);
                }
                inte.putExtra("posicio", mArrayList.get(pos).getPos());
                getContext().sendBroadcast(inte);
            }
        });

        return rowView;
    }
}
