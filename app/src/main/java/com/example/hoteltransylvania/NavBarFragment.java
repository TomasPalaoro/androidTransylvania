package com.example.hoteltransylvania;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavBarFragment extends Fragment {

    BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vistaFragment = inflater.inflate(R.layout.fragment_nav_bar, container, false);

        bottomNav = vistaFragment.findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    Toast.makeText(getActivity(), "Home", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.perfil:
                    Toast.makeText(getActivity(), "perfil", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.chat:
                    Toast.makeText(getActivity(), "chat", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
            return true;
        });

        return vistaFragment;
    }
}