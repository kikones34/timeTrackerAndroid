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
import android.widget.LinearLayout;
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

        Button crear = findViewById(R.id.formulari_btn_crear);
        Button tornar = findViewById(R.id.formulari_btn_tornar);
        Spinner spr = findViewById(R.id.formulari_spr_tipus_activitat);
        CheckBox cbAutoStart = findViewById(R.id.formulari_check_AutoStart);
        CheckBox cbAutoEnd = findViewById(R.id.formulari_check_AutoEnd);

        Button autoStart_btn = findViewById(R.id.start_time_button);
        Button autoEnd_btn = findViewById(R.id.end_time_button);

        crear.setOnClickListener(btnCrearListener);
        tornar.setOnClickListener(btnTornarListener);
        autoStart_btn.setOnClickListener(autoStartBtnListener);
        autoEnd_btn.setOnClickListener(autoEndBtnListener);
        spr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ConstraintLayout cl = findViewById(R.id.formulari_constraintLayout);

                Spinner spr = findViewById(R.id.formulari_spr_tipus_activitat);

                CheckBox cb1 = findViewById(R.id.formulari_check_AutoStart);
                CheckBox cb2 = findViewById(R.id.formulari_check_AutoEnd);

                if(spr.getSelectedItem().toString().contains("Tasca")) {
                    cl.setVisibility(view.VISIBLE);
                } else {
                    cl.setVisibility(view.GONE);
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        cbAutoStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            LinearLayout ll = findViewById(R.id.automatic_start_desc);
            if (isChecked) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getFragmentManager(),"TimePicker");
                ll.setVisibility(buttonView.VISIBLE);
            } else {
                ll.setVisibility(buttonView.GONE);
            }
            }
        });

        cbAutoEnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout ll = findViewById(R.id.automatic_stop_desc);
            if (isChecked) {
                ll.setVisibility(buttonView.VISIBLE);
            } else {
                ll.setVisibility(buttonView.GONE);
            }
            }
        });
    }

    private View.OnClickListener autoStartBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putInt("id", R.id.start_time_button);

            DialogFragment newFragment = new TimePickerFragment();
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(),"TimePicker");
        }
    };

    private View.OnClickListener autoEndBtnListener = new View.OnClickListener() {
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putInt("id", R.id.end_time_button);

            DialogFragment newFragment = new TimePickerFragment();
            newFragment.setArguments(args);
            newFragment.show(getFragmentManager(),"TimePicker");
        }
    };

    private View.OnClickListener btnCrearListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(LlistaActivitatsActivity.CREAR_ACTIVITAT);

            EditText et_nom = findViewById(R.id.formulari_nom_activitat);
            EditText et_descripcio = findViewById(R.id.formulari_descripcio);
            Spinner scr_tipus = findViewById(R.id.formulari_spr_tipus_activitat);

            String nom = et_nom.getText().toString();
            String descripcio = et_descripcio.getText().toString();
            String tipus = scr_tipus.getSelectedItem().toString();

            if (nom.isEmpty()) {
                Toast toast = Toast.makeText(FormulariCrearActivitatActivity.this, R.string.nom_buit, Toast.LENGTH_SHORT);
                toast.show();
            } else {
                if (descripcio.isEmpty()) {
                    Toast toast = Toast.makeText(FormulariCrearActivitatActivity.this, R.string.descripcio_buida, Toast.LENGTH_SHORT);
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
