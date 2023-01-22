package com.example.hoteltransylvania.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.data.entities.Reserva;
import com.example.hoteltransylvania.fragments.InfoHabitacionFragment;
import com.example.hoteltransylvania.fragments.OcioFragment;
import com.example.hoteltransylvania.fragments.ReservasFragment;
import com.example.hoteltransylvania.viewmodels.ReservaViewModel;
import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity {

    static FragmentManager fragmentManager;
    static ReservasFragment reservasFragment;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    static ReservaViewModel reservaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        reservaViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) factory).get(ReservaViewModel.class);
        //

        fragmentManager = getSupportFragmentManager();

        OcioFragment ocioFragment = new OcioFragment();
        reservasFragment = new ReservasFragment();

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

    /**
     * Reemplaza el fragment principal por el fragment con la información de una habitación seleccionada
     * @param id
     * @param imagen
     * @param precio
     * @param nombre
     * @param descripcion
     */
    public static void mostrarInfoHabitacion(String id, Integer imagen, Double precio, String nombre, String descripcion, int personas){
        InfoHabitacionFragment infoHabitacionFragment = new InfoHabitacionFragment();
        Bundle infoHabitacion = new Bundle();
        infoHabitacion.putString("id",id);
        infoHabitacion.putInt( "imagen" , imagen);
        infoHabitacion.putDouble( "precio" , precio);
        infoHabitacion.putString( "descripcion" , descripcion);
        infoHabitacion.putInt( "personas" , personas);
        infoHabitacionFragment.setArguments(infoHabitacion);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPrincipal, infoHabitacionFragment);
        fragmentTransaction.commit();
    }

    /**
     * Reemplaza el fragment principal por el de reservas
     */
    public static void volverFragment(){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPrincipal, reservasFragment);
        fragmentTransaction.commit();
    }

    public static void reservarHabitacion(String fechaEntrada, String fechaSalida, int personas, String idUsuario, String idHabitacion){
        reservaViewModel.insertarReserva(new Reserva(fechaEntrada,fechaSalida,personas,idUsuario,idHabitacion));
        volverFragment();
    }
}