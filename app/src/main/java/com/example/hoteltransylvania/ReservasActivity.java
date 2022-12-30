package com.example.hoteltransylvania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class ReservasActivity extends AppCompatActivity {
    EditText etdatePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        etdatePicker = (EditText) findViewById(R.id.editTextDatePicker);
        etdatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha();
            }
        });
    }

    private void mostrarSeleccionFecha() {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque enero es 0
                final String fechaSeleccionada = day + " / " + (month+1) + " / " + year;
                etdatePicker.setText(fechaSeleccionada);
            }
        });

        datePickerFragment.show(getSupportFragmentManager(), "datePicker");
    }
}