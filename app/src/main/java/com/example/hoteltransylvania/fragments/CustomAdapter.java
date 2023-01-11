package com.example.hoteltransylvania.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoteltransylvania.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] arrayImagenes;
    String[] nombres;
    LayoutInflater inflater;

    CustomAdapter(Context context, int[] imagenes, String[] nombres){
        this.context = context;
        this.arrayImagenes = imagenes;
        this.nombres = nombres;
        inflater=(LayoutInflater.from(context));
    }
    @Override
    public int getCount() {
        return arrayImagenes.length;
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
        imageView.setImageResource(arrayImagenes[i]);
        textView.setText(nombres[i]);
        return view;
    }
}
