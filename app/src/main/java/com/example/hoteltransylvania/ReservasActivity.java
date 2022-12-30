package com.example.hoteltransylvania;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ReservasActivity extends AppCompatActivity {

    static boolean entradaIntroducida, salidaIntroducida;
    EditText pickerEntrada, pickerSalida, numeroAdultos, numeroMenores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        pickerEntrada = (EditText) findViewById(R.id.datePickerEntrada);
        entradaIntroducida = false;
        pickerSalida = (EditText) findViewById(R.id.datePickerSalida);
        salidaIntroducida = false;

        pickerEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha(pickerEntrada);
                entradaIntroducida = true;
            }
        });
        pickerSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha(pickerSalida);
                salidaIntroducida = true;
            }
        });

        numeroAdultos = (EditText) findViewById(R.id.numeroAdultos);
        numeroMenores = (EditText) findViewById(R.id.numeroMenores);
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

    public void buscarPulsado(View view) {
        if (entradaIntroducida && salidaIntroducida){

            int adultos = Integer.parseInt(numeroAdultos.getText().toString());
            int menores = Integer.parseInt(numeroMenores.getText().toString());
            String fechaEntrada = pickerEntrada.getText().toString();
            String fechaSalida = pickerSalida.getText().toString();

            if (adultos<1) Toast.makeText(this, "Debe ir al menos un adulto", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, adultos+" adultos "+menores+" niños "+fechaEntrada+" - "+fechaSalida, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Introduce una fecha", Toast.LENGTH_SHORT).show();
        }

    }
}