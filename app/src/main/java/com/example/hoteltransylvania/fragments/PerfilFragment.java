package com.example.hoteltransylvania.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hoteltransylvania.R;

public class PerfilFragment extends Fragment {

    TextView tvEmail, tvNombre;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("datos", 0);
        tvEmail = view.findViewById(R.id.etCorreoPerfil);
        tvEmail.setText(sharedPreferences.getString("email","Email"));
        tvNombre = view.findViewById(R.id.etNombrePerfil);
        tvNombre.setText(sharedPreferences.getString("nombre","Nombre completo"));
        return view;
    }
}