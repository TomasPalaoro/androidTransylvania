package com.example.hoteltransylvania;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity {

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.reservas:
                        hacerIntent(ReservasActivity.class);
                        break;
                    case R.id.ocio:
                        hacerIntent(OcioActivity.class);
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }

    public void irReservas(View view) {
        hacerIntent(ReservasActivity.class);
    }

    public void irOcio(View view) {
        hacerIntent(OcioActivity.class);
    }

    public void hacerIntent(Class<?> activityDestino){
        Intent intent = new Intent(this, activityDestino).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}