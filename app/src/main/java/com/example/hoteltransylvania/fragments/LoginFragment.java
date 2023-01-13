package com.example.hoteltransylvania.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.activities.InicioActivity;
import com.example.hoteltransylvania.activities.MainActivity;

public class LoginFragment extends Fragment {

    TextView cajaNombre, cajaPass, cajaPass2;
    Switch switchRecordar;

    String nombre = "";
    String pass = "";
    String nombreGuardado, passGuardado;

    Button botonRegistrarse, botonEntrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        botonRegistrarse = view.findViewById(R.id.registrarme);
        botonEntrar = view.findViewById(R.id.boton);
        cajaNombre = view.findViewById(R.id.email);
        cajaPass = view.findViewById(R.id.pass);
        switchRecordar = view.findViewById(R.id.switchRecordar);
        SharedPreferences preferencias = getActivity().getApplicationContext().getSharedPreferences("datos", 0);
        if (preferencias.getBoolean("recordar",false)){
            cajaNombre.setText(preferencias.getString("username",""));
            switchRecordar.setChecked(true);
        }

        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.cambiarFragment("registro");
            }
        });

        botonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferencias = getActivity().getApplicationContext().getSharedPreferences("datos", 0);
                nombreGuardado = preferencias.getString("username","");
                passGuardado = preferencias.getString("password","");

                //INICIAR SESIÓN
                nombre = cajaNombre.getText().toString();
                pass = cajaPass.getText().toString();
                if (!nombre.equals(nombreGuardado)) Toast.makeText(getActivity(), "Nombre incorrecto", Toast.LENGTH_SHORT).show();
                else if (!pass.equals(passGuardado)) Toast.makeText(getActivity(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                else{
                    SharedPreferences.Editor editor = preferencias.edit();
                    if (switchRecordar.isChecked()){
                        editor.putBoolean("recordar",true);
                        editor.apply();
                    }else{
                        editor.putBoolean("recordar",false);
                        editor.apply();
                    }
                    Toast.makeText(getActivity(), "login correcto", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), InicioActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        return view;
    }
}