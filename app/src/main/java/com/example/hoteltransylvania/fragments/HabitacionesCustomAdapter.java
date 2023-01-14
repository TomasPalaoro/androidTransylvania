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
    ArrayList<String> descripcion;
    LayoutInflater inflater;

    HabitacionesCustomAdapter(Context context, ArrayList<Integer> imagenes, ArrayList<String> descripcion){
        this.context = context;
        this.arrayImagenes = imagenes;
        this.descripcion = descripcion;
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
        view = inflater.inflate(R.layout.contenedor_imagen,viewGroup,false);
        ImageView imageView = view.findViewById(R.id.miImagen);
        TextView textView = view.findViewById(R.id.miTexto);
        imageView.setImageResource(arrayImagenes.get(i));
        textView.setText(descripcion.get(i));
        return view;
    }
}
