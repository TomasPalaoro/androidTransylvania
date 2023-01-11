package com.example.hoteltransylvania.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hoteltransylvania.activities.InicioActivity;
import com.example.hoteltransylvania.activities.MainActivity;
import com.example.hoteltransylvania.activities.PerfilActivity;
import com.example.hoteltransylvania.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBarFragment extends Fragment {

    BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaFragment = inflater.inflate(R.layout.fragment_nav_bar, container, false);

        bottomNav = vistaFragment.findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            Class<?> activityDestino = null;
            switch (item.getItemId()){
                case R.id.home:
                    activityDestino = InicioActivity.class;
                    break;
                case R.id.perfil:
                    activityDestino = PerfilActivity.class;
                    break;
                /*case R.id.chat:
                    activityDestino = MainActivity.class;
                    break;*/
                default:
                    activityDestino = MainActivity.class;
                    break;
            }
            if (!getActivity().getClass().equals(activityDestino)){
                Intent i = new Intent(getActivity().getApplicationContext(), activityDestino).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
            return true;
        });

        return vistaFragment;
    }
}