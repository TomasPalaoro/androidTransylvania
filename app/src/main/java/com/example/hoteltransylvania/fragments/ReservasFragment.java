package com.example.hoteltransylvania.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.data.entities.Reserva;
import com.example.hoteltransylvania.viewmodels.HabitacionViewModel;
import com.example.hoteltransylvania.viewmodels.ReservaViewModel;

public class ReservasFragment extends Fragment {

    Button buscar;
    int adultos,menores;
    String fechaEntrada, fechaSalida,idUsuario,idHabitacion;
    SharedPreferences sharedPreferences;
    //FragmentContainerView fragmentContainerView;
    HabitacionesFragment habitacionesFragment;
    FragmentManager fragmentManager;
    int precioMax;
    TextView mensajeReservas;

    static boolean entradaIntroducida, salidaIntroducida;
    EditText pickerEntrada, pickerSalida, numeroAdultos, numeroMenores, numeroPrecio;

    //CONEXION CON VIEW MODEL
    private HabitacionViewModel habitacionViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        habitacionViewModel = new ViewModelProvider(this).get(HabitacionViewModel.class);
    }
    ////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);

        sharedPreferences = getActivity().getSharedPreferences("datos", 0);
        idUsuario = sharedPreferences.getString("email","Tomas");
        //idHabitacion = "1";
        //precioMax = 500;
        inicializar();
        buscar = view.findViewById(R.id.botonReservar);
        mensajeReservas = view.findViewById(R.id.mensajeReservas);

        //instanciar busqueda de habitaciones
        fragmentManager = getChildFragmentManager();

        pickerEntrada = view.findViewById(R.id.datePickerEntrada);
        entradaIntroducida = false;
        pickerSalida = view.findViewById(R.id.datePickerSalida);
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

        numeroAdultos = view.findViewById(R.id.numeroAdultos);
        numeroMenores = view.findViewById(R.id.numeroMenores);
        numeroPrecio = view.findViewById(R.id.numeroPrecio);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (entradaIntroducida && salidaIntroducida){
                    mensajeReservas.setText("");
                    if (numeroAdultos.getText().toString().equals("")) adultos = 1;
                    else adultos = Integer.parseInt(numeroAdultos.getText().toString());
                    if (numeroMenores.getText().toString().equals("")) menores = 0;
                    else menores = Integer.parseInt(numeroMenores.getText().toString());
                    if (numeroPrecio.getText().toString().equals("")) precioMax = 1000;
                    else precioMax = Integer.parseInt(numeroPrecio.getText().toString());

                    fechaEntrada = pickerEntrada.getText().toString();
                    fechaSalida = pickerSalida.getText().toString();

                    //
                    //Guardamos las fechas para que se puedan usar al insertar una reserva en InicioActivity
                    //
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("fechaEntrada",fechaEntrada);
                    editor.putString("fechaSalida",fechaSalida);
                    editor.apply();

                    if (adultos<0) mensajeReservas.setText("Debe ir al menos un adulto");
                    else{
                        try {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                        } catch (Exception e) {}

                        //INICIAR BUSQUEDA
                        iniciarFragmentHabitaciones();
                    }
                }
                else{
                    mensajeReservas.setText("Introduce una fecha");
                }
            }
        });

        return view;
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

        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    /**
     * Muestra el fragment que contiene el listado de habitaciones y filtra por los parámetros numeroPersonas y precioMaximo
     */
    public void iniciarFragmentHabitaciones(){
        habitacionesFragment = new HabitacionesFragment();
        Bundle arguments = new Bundle();
        arguments.putInt( "numeroPersonas" , (adultos+menores));
        arguments.putInt("precioMaximo", precioMax);
        habitacionesFragment.setArguments(arguments);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contenedorHabitaciones, habitacionesFragment);
        fragmentTransaction.commit();
    }

    /**
     * Crea las habitaciones por defecto si no existen en la base de datos
     */
    public void inicializar(){
        habitacionViewModel.insertarHabitacion(new Habitacion("1", 2,"una habitacion",23,R.drawable.habitacion));
        habitacionViewModel.insertarHabitacion(new Habitacion("2", 1,"otra habitacion",10,R.drawable.habitacion));
        habitacionViewModel.insertarHabitacion(new Habitacion("3", 4,"y otra habitacion",50,R.drawable.habitacion));
    }
}