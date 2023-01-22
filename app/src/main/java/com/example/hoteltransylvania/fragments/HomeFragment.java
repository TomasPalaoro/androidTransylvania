package com.example.hoteltransylvania.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.activities.InicioActivity;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.viewmodels.HabitacionViewModel;

public class HomeFragment extends Fragment {

    Button bRes, bHab, bOcio;

    //CONEXION CON VIEW MODEL
    private HabitacionViewModel habitacionViewModel;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        habitacionViewModel = new ViewModelProvider(this).get(HabitacionViewModel.class);
    }
    ////
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        
        bRes = view.findViewById(R.id.bRes);
        bHab = view.findViewById(R.id.bHab);
        bOcio = view.findViewById(R.id.bOcio);

        bRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.irFragment("reservas");
            }
        });
        bHab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.irFragment("habitaciones");
            }
        });
        bOcio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InicioActivity.irFragment("ocio");
            }
        });

        crearHabitaciones();

        return view;
    }

    /**
     * Crea las habitaciones por defecto si no existen en la base de datos
     */
    public void crearHabitaciones(){
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Descanso Macabro",
                1,
                "Esta habitación es perfecta para personas que viajan solas y quieren tener una noche de sueño cómoda y espeluznante. Las paredes de la habitación están adornadas con carteles de películas de terror clásicas y un retrato de una figura fantasmal que cuelga sobre la cama. El baño también ha sido decorado con un toque macabro, con una bañera con patas y una cortina de ducha negra y naranja. El lavabo está decorado con un dosificador de jabón en forma de calavera y un portacepillos a juego.",
                45,
                R.drawable.habitacion));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Stranger Things",
                2,
                "Esta habitación es perfecta para los fanáticos de \"Stranger Things\". Es una experiencia divertida e inmersiva que te transportará a los años 80 y te sumergirá en el mundo de la serie. Al entrar en la habitación te encontrarñas con un mural de la icónica pared alfabeto del programa. La habitación también tiene una característica única, una puerta oculta que conduce a una habitación secreta, decorada con un colorido letrero de neón.",
                65,
                R.drawable.habstrangerthings));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Asesinato misterioso",
                4,
                "La característica principal de esta habitación es una serie de pistas y acertijos que los visitantes pueden resolver para descubrir la identidad del \"asesino\". Estas pistas se pueden encontrar ocultas por toda la habitación, como mensajes crípticos escritos en las paredes, compartimentos ocultos y pasadizos secretos. Esta habitación es perfecta para los huéspedes que aman un buen misterio y disfrutan resolviendo acertijos.",
                80,
                R.drawable.habasesinatomisterioso));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Halloween",
                1,
                "Al entrar en la habitación, serás recibido por una iluminación tenue y sombras espeluznantes que bailan a través de las paredes. La habitación está decorada con telarañas y calabazas espeluznantes. La cama está hecha con sábanas negras y naranjas y una funda nórdica blanca fantasmal. Las mesitas de noche están adornadas con candelabros y dulces en forma de calavera. Las paredes están adornadas con carteles de películas de terror clásicas y un retrato de una figura fantasmal que cuelga sobre la cama.",
                40,
                R.drawable.habhalloween));
        habitacionViewModel.insertarHabitacion(new Habitacion(
                "Hab. Infantil",
                3,
                "Esta es la habitación perfecta para los más pequeños de la familia. Disfrutarán de una ambientación tenebrosa a la vez que amistosa. Las mesitas de noche están adornadas con candelabros y dulces en forma de calavera. Se puede activar un sistema de sonido incorporado en la habitación, que reproduce música y distintos efectos de sonido para crear el ambiente.",
                50,
                R.drawable.habinfantil));
    }
}