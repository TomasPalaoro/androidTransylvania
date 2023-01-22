package com.example.hoteltransylvania.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.data.entities.Reserva;
import com.example.hoteltransylvania.fragments.HabitacionesFragment;
import com.example.hoteltransylvania.fragments.InfoHabitacionFragment;
import com.example.hoteltransylvania.fragments.OcioFragment;
import com.example.hoteltransylvania.fragments.PerfilFragment;
import com.example.hoteltransylvania.fragments.ReservasFragment;
import com.example.hoteltransylvania.viewmodels.ReservaViewModel;
import com.google.android.material.navigation.NavigationView;

public class InicioActivity extends AppCompatActivity {

    static FragmentManager fragmentManager;
    static ReservasFragment reservasFragment;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    static ReservaViewModel reservaViewModel;
    static OcioFragment ocioFragment;
    static HabitacionesFragment habitacionesFragment;
    static PerfilFragment perfilFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //
        ViewModelProvider.AndroidViewModelFactory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication());
        reservaViewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) factory).get(ReservaViewModel.class);
        //

        fragmentManager = getSupportFragmentManager();

        habitacionesFragment = new HabitacionesFragment();
        ocioFragment = new OcioFragment();
        reservasFragment = new ReservasFragment();
        perfilFragment = new PerfilFragment();

        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.miDrawer);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.habitaciones:
                        irFragment("habitaciones");
                        break;
                    case R.id.reservas:
                        irFragment("reservas");
                        break;
                    case R.id.ocio:
                        irFragment("ocio");
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
     * @param descripcion
     * @param personas
     * @param esReserva
     */
    public static void mostrarInfoHabitacion(String id, Integer imagen, Double precio, String descripcion, int personas, boolean esReserva){
        InfoHabitacionFragment infoHabitacionFragment = new InfoHabitacionFragment();
        Bundle infoHabitacion = new Bundle();
        infoHabitacion.putString("id",id);
        infoHabitacion.putInt( "imagen" , imagen);
        infoHabitacion.putDouble( "precio" , precio);
        infoHabitacion.putString( "descripcion" , descripcion);
        infoHabitacion.putInt( "personas" , personas);
        infoHabitacion.putBoolean("esReserva", esReserva);
        infoHabitacionFragment.setArguments(infoHabitacion);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentPrincipal, infoHabitacionFragment);
        fragmentTransaction.commit();
    }

    /**
     * Reemplaza el fragment principal por el indicado
     */
    public static void irFragment(String nombreFragment){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (nombreFragment){
            case "perfil":
                fragmentTransaction.replace(R.id.fragmentPrincipal, perfilFragment);
                break;
            case "ocio":
                fragmentTransaction.replace(R.id.fragmentPrincipal, ocioFragment);
                break;
            case "reservas":
                fragmentTransaction.replace(R.id.fragmentPrincipal, reservasFragment);
                break;
            case "habitaciones":
                fragmentTransaction.replace(R.id.fragmentPrincipal, habitacionesFragment);
                break;
        }
        fragmentTransaction.commit();
    }

    public static void reservarHabitacion(String fechaEntrada, String fechaSalida, int personas, String idUsuario, String idHabitacion){
        reservaViewModel.insertarReserva(new Reserva(fechaEntrada,fechaSalida,personas,idUsuario,idHabitacion));
        irFragment("reservas");
    }
}