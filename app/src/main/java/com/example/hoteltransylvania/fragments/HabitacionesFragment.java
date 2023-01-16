package com.example.hoteltransylvania.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.viewmodels.HabitacionViewModel;

import java.util.ArrayList;

public class HabitacionesFragment extends Fragment {

    GridView grid;

    ArrayList<Integer> imagenes = new ArrayList<Integer>();
    ArrayList<String> descripciones = new ArrayList<String>();

    TextView mensaje;
    int personas, precioMax;

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
        View view = inflater.inflate(R.layout.fragment_habitaciones, container, false);

        grid = view.findViewById(R.id.gridHabitaciones);

        mensaje = view.findViewById(R.id.mensajeHabitacion);
        Bundle arguments = getArguments();
        personas = arguments.getInt("numeroPersonas");
        precioMax = arguments.getInt("precioMaximo");
        mensaje.setText(personas+" "+precioMax);

        return view;
    }

    public void buscar(){
        habitacionViewModel.devolverTodasHabitaciones().observe(getViewLifecycleOwner(),listaHabitaciones->{
            if(listaHabitaciones==null){
                System.out.println("null");
            }else{
                for (Habitacion hab :listaHabitaciones){
                    imagenes.add(hab.getImagen());
                    descripciones.add((hab.getDescrip()));
                }
                HabitacionesCustomAdapter habitacionesCustomAdapter = new HabitacionesCustomAdapter(getActivity().getApplicationContext(),imagenes,descripciones);
                grid.setAdapter(habitacionesCustomAdapter);
            }
        });
    }
}