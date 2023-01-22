package com.example.hoteltransylvania.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.data.entities.Reserva;
import com.example.hoteltransylvania.viewmodels.HabitacionViewModel;
import com.example.hoteltransylvania.viewmodels.ReservaViewModel;

import java.util.ArrayList;


public class HistorialReservasFragment extends Fragment {

    GridView grid;
    ArrayList<Integer> ids = new ArrayList<Integer>();
    ArrayList<String> fEntradas = new ArrayList<String>();
    ArrayList<String> fSalidas = new ArrayList<String>();
    ArrayList<String> idsHabitaciones = new ArrayList<String>();

    //CONEXION CON VIEW MODEL
    private ReservaViewModel reservaViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);
    }
    ////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historial_reservas, container, false);

        grid = view.findViewById(R.id.listaReservas);

        reservaViewModel.devolverTodosReservas().observe(getViewLifecycleOwner(),listaReservas->{
            if(listaReservas==null){
                System.out.println("null");
            }else{
                System.out.println("entra");
                for (Reserva res :listaReservas){
                    if (!ids.contains(res.getId())){
                        ids.add(res.getId());
                        fEntradas.add(res.getFecha_entrada());
                        fSalidas.add(res.getFecha_salida());
                        idsHabitaciones.add(res.getIdHabitacion());
                    }
                }
                HistorialCustomAdapter historialCustomAdapter = new HistorialCustomAdapter(getActivity().getApplicationContext(),fEntradas,fSalidas,idsHabitaciones);
                grid.setAdapter(historialCustomAdapter);
            }
        });


        return view;
    }
}