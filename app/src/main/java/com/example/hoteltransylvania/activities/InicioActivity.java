package com.example.hoteltransylvania.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.fragments.OcioFragment;
import com.example.hoteltransylvania.fragments.ReservasFragment;
import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        FragmentManager fragmentManager = getSupportFragmentManager();

        OcioFragment ocioFragment = new OcioFragment();
        ReservasFragment reservasFragment = new ReservasFragment();

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.miDrawer);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.reservas:
                        fragmentTransaction.replace(R.id.fragmentPrincipal, reservasFragment);
                        fragmentTransaction.commit();
                        break;
                    case R.id.ocio:
                        fragmentTransaction.replace(R.id.fragmentPrincipal, ocioFragment);
                        fragmentTransaction.commit();
                        break;
                    default:
                        break;
                }
                drawerLayout.closeDrawers();
                return false;
            }
        });
    }
    /*
    public void irReservas(View view) {
        hacerIntent(ReservasActivity.class);
    }

    public void irOcio(View view) {
        hacerIntent(OcioActivity.class);
    }

    public void hacerIntent(Class<?> activityDestino){
        Intent intent = new Intent(this, activityDestino).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }*/
}