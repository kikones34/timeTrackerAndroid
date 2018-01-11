package com.example.joans.timetracker;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
        CheckBox cbAutoStart = findViewById(R.id.formulari_check_AutoStart);
        CheckBox cbAutoEnd = findViewById(R.id.formulari_check_AutoEnd);

        final ConstraintLayout decorators = findViewById(R.id.formulari_decorators);
        final LinearLayout auto_start_decorator = findViewById(R.id.automatic_start_desc);
        final LinearLayout auto_end_decorator = findViewById(R.id.automatic_stop_desc);

        decorators.setVisibility(View.GONE);
        auto_start_decorator.setVisibility(View.GONE);
        auto_end_decorator.setVisibility(View.GONE);

        Button autoStart_btn = findViewById(R.id.start_time_button);
        Button autoEnd_btn = findViewById(R.id.end_time_button);

        crear.setOnClickListener(btnCrearListener);
        tornar.setOnClickListener(btnTornarListener);
        autoStart_btn.setOnClickListener(autoStartBtnListener);
        autoEnd_btn.setOnClickListener(autoEndBtnListener);

        RadioGroup rg = findViewById(R.id.radio_projecte_tasca);
        RadioButton radio_projecte = findViewById(R.id.radio_projecte);
        radio_projecte.setChecked(true);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton rbTasca = findViewById(R.id.radio_tasca);
                if (rbTasca.isChecked()) {
                    decorators.setVisibility(View.VISIBLE);
                } else {
                    decorators.setVisibility(View.GONE);
                }
            }
        });

        cbAutoStart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                auto_start_decorator.setVisibility(buttonView.VISIBLE);
            } else {
                auto_start_decorator.setVisibility(buttonView.GONE);
            }
            }
        });

        cbAutoEnd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                auto_end_decorator.setVisibility(buttonView.VISIBLE);
            } else {
                auto_end_decorator.setVisibility(buttonView.GONE);
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
            int a = (R.id.end_time_button);
            System.out.println(a);
            args.putInt("id", (R.id.end_time_button));

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
            RadioGroup act_tipus = findViewById(R.id.radio_projecte_tasca);

            String nom = et_nom.getText().toString();
            String descripcio = et_descripcio.getText().toString();
            RadioButton selected_radio = findViewById(act_tipus.getCheckedRadioButtonId());
            String tipus = selected_radio.getText().toString();

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
