package com.example.joans.timetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by Jiacheny on 2018/1/11.
 */

public class MesDetallsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_detalls);

        Bundle b = getIntent().getExtras();

        DadesActivitat da = null;
        if (b != null) {
            da = (DadesActivitat) b.getSerializable("activitat");
        }

        TextView nom = (TextView) findViewById(R.id.mes_detalls_nom);
        TextView descripcio = (TextView) findViewById(R.id.mes_detalls_descripcio);
        TextView data_inici = (TextView) findViewById(R.id.mes_detalls_data_inici);
        TextView data_fi = (TextView) findViewById(R.id.mes_detalls_data_fi);
        TextView durada = (TextView) findViewById(R.id.mes_detalls_durada);

        nom.setText(da.getNom());
        descripcio.setText(da.getDescripcio());
        SimpleDateFormat formatter = new SimpleDateFormat();
        data_inici.setText(formatter.format(da.getDataInicial()));
        data_fi.setText(formatter.format(da.getDataFinal()));
        durada.setText(String.format("%02dh %02dm %02ds", da.getHores(), da.getMinuts(), da.getSegons()));
    }
}
