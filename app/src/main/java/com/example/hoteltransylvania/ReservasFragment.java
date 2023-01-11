package com.example.hoteltransylvania;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class ReservasFragment extends Fragment {

    Button buscar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);

        buscar = view.findViewById(R.id.botonReservar);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entradaIntroducida && salidaIntroducida){

                    int adultos = Integer.parseInt(numeroAdultos.getText().toString());
                    int menores = Integer.parseInt(numeroMenores.getText().toString());
                    String fechaEntrada = pickerEntrada.getText().toString();
                    String fechaSalida = pickerSalida.getText().toString();

                    if (adultos<1) Toast.makeText(getActivity().getApplicationContext(), "Debe ir al menos un adulto", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(getActivity(), adultos+" adultos "+menores+" niños "+fechaEntrada+" - "+fechaSalida, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Introduce una fecha", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pickerEntrada = (EditText) view.findViewById(R.id.datePickerEntrada);
        entradaIntroducida = false;
        pickerSalida = (EditText) view.findViewById(R.id.datePickerSalida);
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

        numeroAdultos = (EditText) view.findViewById(R.id.numeroAdultos);
        numeroMenores = (EditText) view.findViewById(R.id.numeroMenores);
        
        return view;
    }

    static boolean entradaIntroducida, salidaIntroducida;
    EditText pickerEntrada, pickerSalida, numeroAdultos, numeroMenores;
    

    private void mostrarSeleccionFecha(final EditText editText) {
        DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 porque enero es 0
                final String fechaSeleccionada = day + " / " + (month+1) + " / " + year;
                editText.setText(fechaSeleccionada);
            }
        });

        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }
}