package com.example.hoteltransylvania.fragments;

import android.content.SharedPreferences;
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
    String id,descripcion;
    Button reservar, volver;
    int personas;

    SharedPreferences sharedPreferences;

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

        sharedPreferences = getActivity().getSharedPreferences("datos", 0);

        Bundle infoHabitacion = getArguments();
        id = infoHabitacion.getString("id","Habitacion");
        imagen = infoHabitacion.getInt("imagen",R.drawable.habitacion);
        precio = infoHabitacion.getDouble("precio",0);
        descripcion = infoHabitacion.getString("descripcion","");
        personas = infoHabitacion.getInt("personas",0);

        ivImagen.setImageResource(imagen);
        tvPrecio.setText(precio+"€");
        tvNombre.setText(id);
        tvDescripcion.setText(descripcion);

        if (!infoHabitacion.getBoolean("esReserva",false)) reservar.setVisibility(View.GONE);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!infoHabitacion.getBoolean("esReserva",false)) InicioActivity.irFragment("habitaciones");
                else InicioActivity.irFragment("reservas");
            }
        });

        reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.reservarHabitacion(sharedPreferences.getString("fechaEntrada",""),sharedPreferences.getString("fechaSalida",""),personas,sharedPreferences.getString("email",""),id);
            }
        });

        return view;
    }


}