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

public class HabitacionesCustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> arrayImagenes;
    ArrayList<String> nombres;
    ArrayList<Double> precios;
    LayoutInflater inflater;

    HabitacionesCustomAdapter(Context context, ArrayList<Integer> imagenes, ArrayList<String> id, ArrayList<Double> precios){
        this.context = context;
        this.arrayImagenes = imagenes;
        this.nombres = id;
        this.precios = precios;
        inflater=(LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return arrayImagenes.size();
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
        view = inflater.inflate(R.layout.imagen_habitacion,viewGroup,false);
        ImageView imageView = view.findViewById(R.id.miImagen);
        TextView descr = view.findViewById(R.id.miTexto);
        TextView prec = view.findViewById(R.id.miPrecio);
        imageView.setImageResource(arrayImagenes.get(i));
        descr.setText(nombres.get(i));
        prec.setText(precios.get(i).toString()+"€");
        return view;
    }
}
