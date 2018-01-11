package com.example.joans.timetracker;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;

/**
 * Created by Jiacheny on 2018/1/10.
 */

public class FormulariCrearActivitatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari_activitat);

        Button crear = (Button) findViewById(R.id.formulari_btn_crear);
        Button tornar = (Button) findViewById(R.id.formulari_btn_tornar);
        Spinner spr = (Spinner) findViewById(R.id.formulari_spr_tipus_activitat);
        CheckBox cbAutoStart = (CheckBox) findViewById(R.id.formulari_check_AutoStart);
        CheckBox cbAutoEnd = (CheckBox) findViewById(R.id.formulari_check_AutoEnd);

        crear.setOnClickListener(btnCrearListener);
        tornar.setOnClickListener(btnTornarListener);
        spr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.formulari_constraintLayout);

                Spinner spr = (Spinner) findViewById(R.id.formulari_spr_tipus_activitat);

                CheckBox cb1 = (CheckBox) findViewById(R.id.formulari_check_AutoStart);
                TextView tv1 = (TextView) findViewById(R.id.formulari_check_AutoStart_tv);
                CheckBox cb2 = (CheckBox) findViewById(R.id.formulari_check_AutoEnd);
                TextView tv2 = (TextView) findViewById(R.id.formulari_check_AutoEnd_tv);

                TextView h = (TextView) findViewById(R.id.autoEnd_h);
                TextView m = (TextView) findViewById(R.id.autoEnd_m);
                TextView s = (TextView) findViewById(R.id.autoEnd_s);

                EditText hour = (EditText) findViewById(R.id.formulari_time_autoEnd_hour);
                EditText min = (EditText) findViewById(R.id.formulari_time_autoEnd_min);
                EditText sec = (EditText) findViewById(R.id.formulari_time_autoEnd_sec);

                if(spr.getSelectedItem().toString().contains("Tasca")) {
                    cl.setVisibility(view.VISIBLE);
                    cb1.setVisibility(view.VISIBLE);
                    cb2.setVisibility(view.VISIBLE);
                    tv1.setVisibility(view.VISIBLE);
                    tv2.setVisibility(view.VISIBLE);
                } else {
                    cb1.setVisibility(view.GONE);
                    cb2.setVisibility(view.GONE);
                    tv1.setVisibility(view.GONE);
                    tv2.setVisibility(view.GONE);
                    h.setVisibility(view.GONE);
                    m.setVisibility(view.GONE);
                    s.setVisibility(view.GONE);
                    hour.setVisibility(view.GONE);
                    min.setVisibility(view.GONE);
                    sec.setVisibility(view.GONE);
                    cl.setVisibility(view.GONE);
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbAutoStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView tp = (TextView) findViewById(R.id.formulari_time_autoStart);
                if (isChecked) {
                    DialogFragment newFragment = new TimePickerFragment();
                    newFragment.show(getFragmentManager(),"TimePicker");
                    tp.setVisibility(buttonView.VISIBLE);
                } else {
                    tp.setVisibility(buttonView.GONE);
                }
            }
        });

        cbAutoEnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView h = (TextView) findViewById(R.id.autoEnd_h);
                TextView m = (TextView) findViewById(R.id.autoEnd_m);
                TextView s = (TextView) findViewById(R.id.autoEnd_s);

                EditText hour = (EditText) findViewById(R.id.formulari_time_autoEnd_hour);
                EditText min = (EditText) findViewById(R.id.formulari_time_autoEnd_min);
                EditText sec = (EditText) findViewById(R.id.formulari_time_autoEnd_sec);

                if (isChecked) {
                    h.setVisibility(buttonView.VISIBLE);
                    m.setVisibility(buttonView.VISIBLE);
                    s.setVisibility(buttonView.VISIBLE);
                    hour.setVisibility(buttonView.VISIBLE);
                    min.setVisibility(buttonView.VISIBLE);
                    sec.setVisibility(buttonView.VISIBLE);
                } else {
                    h.setVisibility(buttonView.GONE);
                    m.setVisibility(buttonView.GONE);
                    s.setVisibility(buttonView.GONE);
                    hour.setVisibility(buttonView.GONE);
                    min.setVisibility(buttonView.GONE);
                    sec.setVisibility(buttonView.GONE);
                }
            }
        });
    }

    private View.OnClickListener btnCrearListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LlistaActivitatsActivity.CREAR_ACTIVITAT);

            EditText et_nom = (EditText) findViewById(R.id.formulari_nom_activitat);
            EditText et_descripcio = (EditText) findViewById(R.id.formulari_descripcio);
            Spinner scr_tipus = (Spinner) findViewById(R.id.formulari_spr_tipus_activitat);

            String nom = et_nom.getText().toString();
            String descripcio = et_descripcio.getText().toString();
            String tipus = scr_tipus.getSelectedItem().toString();

            if (nom.isEmpty()) {
                Toast toast = Toast.makeText(FormulariCrearActivitatActivity.this, "nom buit", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (descripcio.isEmpty()) {
                    Toast toast = Toast.makeText(FormulariCrearActivitatActivity.this, "descripció buit", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    intent.putExtra("nom", nom);
                    intent.putExtra("descripcio", descripcio);
                    intent.putExtra("tipus", tipus);

                    sendBroadcast(intent);
                    finish();
                }
            }
        }
    };

    private View.OnClickListener btnTornarListener = new View.OnClickListener() {
        public void onClick(View v) {
            finish();
        }
    };
}
