package com.example.hoteltransylvania.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.hoteltransylvania.data.entities.Usuario;
import com.example.hoteltransylvania.data.repositories.UsuarioRepository;

import java.util.List;

public class UsuarioViewModel extends AndroidViewModel {
    private final UsuarioRepository usuarioRepository;
    private final LiveData<List<Usuario>> listadoUsuarios;

    public UsuarioViewModel(@NonNull Application application) {
        super(application);
        this.usuarioRepository = new UsuarioRepository(application);
        this.listadoUsuarios = usuarioRepository.rDevolverTodosUsuarios();
    }

    public LiveData<List<Usuario>> devolverTodosUsuarios(){
        return listadoUsuarios;
    }

    public void insertarUsuario(Usuario usuario){
        usuarioRepository.rInsertarUsuario(usuario);
    }

    public void eliminarUsuario(Usuario usuario){
        usuarioRepository.rEliminarUsuario(usuario);
    }

    public LiveData<Usuario> devolverUsuarioConId(String miD, String miPassword){
        return usuarioRepository.rDevolverUsuarioConId(miD, miPassword);
    }
}