package com.example.hoteltransylvania.fragments;

import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.activities.InicioActivity;

public class InfoHabitacionFragment extends Fragment {

    ImageView ivImagen;
    TextView tvPrecio, tvNombre, tvDescripcion;
    Integer imagen;
    double precio;
    String nombre,descripcion;
    Button reservar, volver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_habitacion, container, false);

        tvPrecio = view.findViewById(R.id.tvPrecio);
        tvNombre = view.findViewById(R.id.tvNombre);
        tvDescripcion = view.findViewById(R.id.tvDescripcion);
        ivImagen = view.findViewById(R.id.imagenHab);
        volver = view.findViewById(R.id.botonVolver);
        reservar = view.findViewById(R.id.botonReserva);

        Bundle infoHabitacion = getArguments();

        imagen = infoHabitacion.getInt("imagen",R.drawable.habitacion);
        precio = infoHabitacion.getDouble("precio",0);
        nombre = infoHabitacion.getString("nombre","Habitación");
        descripcion = infoHabitacion.getString("descripcion","");

        ivImagen.setImageResource(imagen);
        tvPrecio.setText(precio+"€");
        tvNombre.setText(nombre);
        tvDescripcion.setText(descripcion);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.volverFragment();
            }
        });

        return view;
    }


}