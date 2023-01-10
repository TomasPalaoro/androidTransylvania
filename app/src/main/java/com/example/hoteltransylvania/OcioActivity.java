package com.example.hoteltransylvania;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class OcioActivity extends AppCompatActivity {

    int[] misImagenes = {
            R.drawable.ocio1,
            R.drawable.ocio2
    };

    String[] nombres = {
            "La muerte viva",
            "Túnel del terror"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocio);

        GridView grid = findViewById(R.id.grid);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), misImagenes, nombres);
        grid.setAdapter(customAdapter);
    }
}