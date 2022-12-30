package com.example.hoteltransylvania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class ReservasActivity extends AppCompatActivity {
    EditText pickerEntrada, pickerSalida;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        pickerEntrada = (EditText) findViewById(R.id.datePickerEntrada);
        pickerSalida = (EditText) findViewById(R.id.datePickerSalida);

        pickerEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha(pickerEntrada);
            }
        });
        pickerSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha(pickerSalida);
            }
        });
    }

    private void mostrarSeleccionFecha(final EditText editText) {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque enero es 0
                final String fechaSeleccionada = day + " / " + (month+1) + " / " + year;
                editText.setText(fechaSeleccionada);
            }
        });

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }
}