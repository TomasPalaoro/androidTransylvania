package com.example.hoteltransylvania.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hoteltransylvania.R;

public class PerfilActivity extends AppCompatActivity {

    TextView tvEmail, tvNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        SharedPreferences sharedPreferences = getSharedPreferences("datos", 0);
        tvEmail = findViewById(R.id.etCorreoPerfil);
        tvEmail.setText(sharedPreferences.getString("email","Email"));
        tvNombre = findViewById(R.id.etNombrePerfil);
        tvNombre.setText(sharedPreferences.getString("nombre","Nombre completo"));
    }
}