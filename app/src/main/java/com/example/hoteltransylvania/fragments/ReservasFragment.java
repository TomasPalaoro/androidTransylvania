package com.example.hoteltransylvania.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    //static boolean entradaIntroducida, salidaIntroducida;
    EditText pickerEntrada, pickerSalida, numeroAdultos, numeroMenores, numeroPrecio;

    Date fecha1, fecha2;

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

        buscar = view.findViewById(R.id.botonReservar);
        mensajeReservas = view.findViewById(R.id.mensajeReservas);

        //instanciar busqueda de habitaciones
        fragmentManager = getChildFragmentManager();

        pickerEntrada = view.findViewById(R.id.datePickerEntrada);
        //entradaIntroducida = false;
        pickerSalida = view.findViewById(R.id.datePickerSalida);
        //salidaIntroducida = false;

        pickerEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha(pickerEntrada);
                //entradaIntroducida = true;
            }
        });
        pickerSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSeleccionFecha(pickerSalida);
                //salidaIntroducida = true;
            }
        });

        numeroAdultos = view.findViewById(R.id.numeroAdultos);
        numeroMenores = view.findViewById(R.id.numeroMenores);
        numeroPrecio = view.findViewById(R.id.numeroPrecio);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date currentTime = Calendar.getInstance().getTime();

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //Comprueba que los campos no estén vacíos
                //
                if ((!pickerEntrada.getText().toString().equals("")) && (!pickerSalida.getText().toString().equals(""))){
                    //
                    //Verificar fecha de entrada y fecha de salida
                    //
                    try {
                        fecha1 = sdf.parse(pickerEntrada.getText().toString());
                        fecha2 = sdf.parse(pickerSalida.getText().toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int result = fecha1.compareTo(fecha2);
                    int result2 = fecha1.compareTo(currentTime);

                    if (result > 0) mensajeReservas.setText("La fecha de entrada no puede ser posterior a la salida");
                    else{
                        if (result2 < 0) mensajeReservas.setText("Fecha de entrada anterior a la fecha actual");
                        else{
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

                String dia = ""+day;
                if (day<10) dia = "0"+day;

                String mes = ""+(month+1); // +1 porque enero es 0
                if ((month+1)<10) mes = "0"+(month+1);

                final String fechaSeleccionada = dia + "/" + mes + "/" + year;
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
}