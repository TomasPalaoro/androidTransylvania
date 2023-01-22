package com.example.hoteltransylvania.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoteltransylvania.R;

import java.util.ArrayList;

public class HistorialCustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> fEntradas;
    ArrayList<String> fSalidas;
    ArrayList<String> idsHabitaciones;
    LayoutInflater inflater;

    public HistorialCustomAdapter(Context context, ArrayList<String> fEntradas, ArrayList<String> fSalidas, ArrayList<String> idsHabitaciones) {
        this.context = context;
        this.fEntradas = fEntradas;
        this.fSalidas = fSalidas;
        this.idsHabitaciones = idsHabitaciones;
        inflater=(LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return idsHabitaciones.size();
    }
    @Override
    public Object getItem(int i) {
        return null;
    }
    @Override
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.texto_listareservas,viewGroup,false);
        TextView fEntrada = view.findViewById(R.id.fEntrada);
        TextView fSalida = view.findViewById(R.id.fSalida);
        TextView idHabitacion = view.findViewById(R.id.nHabitacion);
        fEntrada.setText(fEntradas.get(i).substring(0,6)+" -");
        fSalida.setText(fSalidas.get(i).substring(0,6));
        idHabitacion.setText(idsHabitaciones.get(i));
        return view;
    }
}
