package com.example.hoteltransylvania.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.activities.MainActivity;
import com.example.hoteltransylvania.data.entities.Usuario;
import com.example.hoteltransylvania.viewmodels.UsuarioViewModel;

import java.util.regex.Pattern;


public class RegistroFragment extends Fragment {
    Button confirmar;

    TextView cajaNombre, cajaPass, cajaPass2, mensajes;

    String nombre = "";
    String pass = "";
    String pass2 = "";

    //CONEXION CON VIEWMODEL
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
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        cajaNombre = view.findViewById(R.id.email);
        cajaPass = view.findViewById(R.id.pass);
        cajaPass2 = view.findViewById(R.id.pass2);
        mensajes = view.findViewById(R.id.warnings);

        confirmar = view.findViewById(R.id.boton);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre = cajaNombre.getText().toString();
                pass = cajaPass.getText().toString();
                pass2 = cajaPass2.getText().toString();
                if (!pass.equals(pass2)) mensajes.setText("Las contraseñas no coinciden");
                else{
                    if (!validarNombre(nombre)) mensajes.setText("Nombre no válido");
                    else if (!validarPass(pass)) mensajes.setText("Contraseña no válida");
                    else{
                        mensajes.setText("");
                        registrar();
                        Toast.makeText(getActivity(), "registro correcto", Toast.LENGTH_SHORT).show();
                        //IR A PANTALLA DE LOGIN
                        MainActivity.cambiarFragment("login");
                    }
                }
            }
        });

        return view;
    }

    public void registrar(){
        usuarioViewModel.insertarUsuario(new Usuario(nombre,pass,"",""));
    }

    public final static boolean validarPass(String pass){
        if (!isValidPassword(pass)) return false;
        int numeros = 0;
        int caracteres = 0;
        for (int i=0; i<pass.length(); i++){
            char c = pass.charAt(i);
            if (Character.isDigit(c)) numeros++;
            else caracteres++;
        }
        if (caracteres<4) return false;
        return numeros >= 3;
    }

    public final static boolean validarNombre(String nombre){
        if (!isValidName(nombre)) return false;
        if (nombre.length()<5) return false;
        for (int i=0; i<nombre.length(); i++){
            char c = nombre.charAt(i);
            if (i==0 && !(c >='a' && c <='z' || c>='A' && c<='Z')){
                return false;
            }
        }
        return true;
    }

    public final static boolean isValidPassword(String target) {
        return Pattern.compile("^(?=.*\\d)(?=.*[a-zA-Z])[a-zA-Z0-9]{4,12}$").matcher(target).matches();
    }

    public final static boolean isValidName(String target) {
        return Pattern.compile("^(?=.*[a-zA-Z가-힣])[a-zA-Z가-힣]{1,}$").matcher(target).matches();
    }
}