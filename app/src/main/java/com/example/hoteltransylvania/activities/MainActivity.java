package com.example.hoteltransylvania.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoteltransylvania.R;

public class MainActivity extends AppCompatActivity {

    TextView cajaNombre, cajaPass, cajaPass2;
    Switch switchRecordar;

    String nombre = "";
    String pass = "";
    String nombreGuardado, passGuardado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cajaNombre = findViewById(R.id.email);
        cajaPass = findViewById(R.id.pass);
        switchRecordar = findViewById(R.id.switchRecordar);
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences("datos", 0);
        if (preferencias.getBoolean("recordar",false)){
            cajaNombre.setText(preferencias.getString("username",""));
            switchRecordar.setChecked(true);
        }
    }

    public void registrar(View view) {
        //IR A PANTALLA DE REGISTRO
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
        finish();
    }

    public void entrarPulsado(View view) {
        SharedPreferences preferencias = getApplicationContext().getSharedPreferences("datos", 0);
        nombreGuardado = preferencias.getString("username","");
        passGuardado = preferencias.getString("password","");

        //INICIAR SESIÓN
        nombre = cajaNombre.getText().toString();
        pass = cajaPass.getText().toString();
        if (!nombre.equals(nombreGuardado)) Toast.makeText(this, "Nombre incorrecto", Toast.LENGTH_SHORT).show();
        else if (!pass.equals(passGuardado)) Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
        else{
            SharedPreferences.Editor editor = preferencias.edit();
            if (switchRecordar.isChecked()){
                editor.putBoolean("recordar",true);
                editor.apply();
            }else{
                editor.putBoolean("recordar",false);
                editor.apply();
            }
            Toast.makeText(this, "login correcto", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, InicioActivity.class);
            startActivity(intent);
            finish();
        }
    }
}