package com.example.hoteltransylvania.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.hoteltransylvania.R;
import com.example.hoteltransylvania.fragments.LoginFragment;
import com.example.hoteltransylvania.fragments.RegistroFragment;

public class MainActivity extends AppCompatActivity {

    static FragmentManager fragmentManager;
    static LoginFragment loginFragment;
    static RegistroFragment registroFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        loginFragment = new LoginFragment();
        registroFragment = new RegistroFragment();
    }

    public static void cambiarFragment(String destino){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (destino){
            case "login":
                fragmentTransaction.replace(R.id.fragmentContainerView, loginFragment);

                break;
            case "registro":
                fragmentTransaction.replace(R.id.fragmentContainerView, registroFragment);
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }
}