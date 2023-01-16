package com.example.hoteltransylvania.fragments;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

    //CONEXION CON VIEW MODEL
    private ReservaViewModel reservaViewModel;
    private HabitacionViewModel habitacionViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
        habitacionViewModel = new ViewModelProvider(this).get(HabitacionViewModel.class);
    }
    ////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reservas, container, false);

        sharedPreferences = getActivity().getSharedPreferences("datos", 0);
        idUsuario = sharedPreferences.getString("email","Tomas");
        idHabitacion = "1";
        adultos = 1;
        menores = 0;
        precioMax = 50;
        inicializar();
        buscar = view.findViewById(R.id.botonReservar);

        //instanciar busqueda de habitaciones
        fragmentManager = getChildFragmentManager();


        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarFragmentHabitaciones();
                if (entradaIntroducida && salidaIntroducida){

                    adultos = Integer.parseInt(numeroAdultos.getText().toString());
                    menores = Integer.parseInt(numeroMenores.getText().toString());
                    fechaEntrada = pickerEntrada.getText().toString();
                    fechaSalida = pickerSalida.getText().toString();

                    if (adultos<1) Toast.makeText(getActivity().getApplicationContext(), "Debe ir al menos un adulto", Toast.LENGTH_SHORT).show();
                    else {
                        iniciarFragmentHabitaciones();
                    }
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

    public void guardarDatos(){
        reservaViewModel.insertarReserva(new Reserva(fechaEntrada,fechaSalida,(adultos+menores),idUsuario,idHabitacion));
        System.out.println(fechaEntrada+" "+fechaSalida+" "+(adultos+menores)+" "+idUsuario+" "+idHabitacion);
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

    public void inicializar(){
        habitacionViewModel.insertarHabitacion(new Habitacion("1","una habitacion",23,111));
        habitacionViewModel.insertarHabitacion(new Habitacion("2","otra habitacion",10,222));
        habitacionViewModel.insertarHabitacion(new Habitacion("3","y otra habitacion",50,333));
    }
}