package com.example.hoteltransylvania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    TextView cajaNombre, cajaPass, cajaPass2;

    String nombre = "";
    String pass = "";
    String pass2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        cajaNombre = findViewById(R.id.email);
        cajaPass = findViewById(R.id.pass);
        cajaPass2 = findViewById(R.id.pass2);
    }

    public void pulsar(View view) {
        nombre = cajaNombre.getText().toString();
        pass = cajaPass.getText().toString();
        pass2 = cajaPass2.getText().toString();
        if (!pass.equals(pass2)) Toast.makeText(this, "No coinciden", Toast.LENGTH_SHORT).show();
        else{
            if (!validarNombre(nombre)) Toast.makeText(this, "Nombre no valido", Toast.LENGTH_SHORT).show();
            else if (!validarPass(pass)) Toast.makeText(this, "Contraseña no valida", Toast.LENGTH_SHORT).show();
            else{
                SharedPreferences preferencias = getApplicationContext().getSharedPreferences("datos", 0);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("username",nombre);
                editor.putString("password",pass);
                editor.apply();
                Toast.makeText(this, "registro correcto", Toast.LENGTH_SHORT).show();
                //IR A PANTALLA DE LOGIN
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
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