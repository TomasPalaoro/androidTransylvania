package com.example.hoteltransylvania.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

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
import com.example.hoteltransylvania.data.entities.Usuario;
import com.example.hoteltransylvania.viewmodels.UsuarioViewModel;

public class LoginFragment extends Fragment {

    TextView cajaNombre, cajaPass, cajaPass2;
    Switch switchRecordar;

    String nombre = "";
    String pass = "";
    String nombreGuardado, passGuardado;

    Button botonRegistrarse, botonEntrar;

    SharedPreferences sharedPreferences;
    TextView mensajes;

    //CONEXION CON VIEW MODEL
    private UsuarioViewModel usuarioViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuarioViewModel = new ViewModelProvider(this).get(UsuarioViewModel.class);
    }
    ////

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        usuarioViewModel.devolverTodosUsuarios().observe(getViewLifecycleOwner(), listaUsuarios->{
            for (Usuario usuario:listaUsuarios){
                System.out.println(usuario.getEmail());
            }
        });
        sharedPreferences = getActivity().getSharedPreferences("datos", 0);

        botonRegistrarse = view.findViewById(R.id.registrarme);
        botonEntrar = view.findViewById(R.id.boton);
        cajaNombre = view.findViewById(R.id.email);
        cajaPass = view.findViewById(R.id.pass);
        switchRecordar = view.findViewById(R.id.switchRecordar);
        mensajes = view.findViewById(R.id.mensaje);
        //SharedPreferences preferencias = getActivity().getApplicationContext().getSharedPreferences("datos", 0);
        if (sharedPreferences.getBoolean("recordar",false)){
            cajaNombre.setText(sharedPreferences.getString("email",""));
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
                nombre = cajaNombre.getText().toString();
                pass = cajaPass.getText().toString();

                comprobarUsuario();
            }
        });
        return view;
    }

    public void continuar(){
        Intent intent = new Intent(getActivity(), InicioActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    public void comprobarUsuario(){
        if(!nombre.equals("") && !pass.equals("")){
            usuarioViewModel.devolverUsuarioConId(nombre,pass).observe(getViewLifecycleOwner(),objetoUsuario->{
                if(objetoUsuario==null){
                    mensajes.setText("Usuario NO encontrado");
                }
                else{
                    Toast.makeText(getActivity(), "BIENVENID@", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("nombre",objetoUsuario.getNombre());
                    editor.putString("email",objetoUsuario.getEmail());
                    if (switchRecordar.isChecked()){
                        editor.putBoolean("recordar",true);
                    }else{
                        editor.putBoolean("recordar",false);
                    }
                    editor.apply();

                    continuar();
                }
            });
        }
        else mensajes.setText("Rellene los campos");
    }
}