package com.example.joans.timetracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jiacheny on 2018/1/11.
 */

public class mesDetallsActivity extends AppCompatActivity {
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
        data_inici.setText(da.getDataInicial().toString());
        data_fi.setText(da.getDataFinal().toString());
        durada.setText(Long.toString(da.getDurada()));
    }
}
