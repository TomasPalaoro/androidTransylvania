package com.example.hoteltransylvania.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.hoteltransylvania.R;

public class OcioFragment extends Fragment {

    int[] misImagenes = {
            R.drawable.ocio1,
            R.drawable.ocio2
    };

    String[] nombres = {
            "La muerte viva",
            "Túnel del terror"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_ocio, container, false);

        GridView grid = vista.findViewById(R.id.grid);
        OcioCustomAdapter customAdapter = new OcioCustomAdapter(getActivity().getApplicationContext(), misImagenes, nombres);
        grid.setAdapter(customAdapter);

        return vista;
    }
}