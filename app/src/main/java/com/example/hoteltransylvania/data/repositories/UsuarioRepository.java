package com.example.hoteltransylvania.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;


import com.example.hoteltransylvania.data.AppDataBase;
import com.example.hoteltransylvania.data.dao.UsuarioDao;
import com.example.hoteltransylvania.data.entities.Usuario;

import java.util.List;

public class UsuarioRepository {

    private UsuarioDao usuarioDao;

    private LiveData<List<Usuario>> listado_usuarios;

    public UsuarioRepository(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        usuarioDao = db.usuarioDao();
        listado_usuarios = usuarioDao.getAll();
    }

    public void rInsertarUsuario(Usuario usuario){
        AppDataBase.dbExecutor.execute(
                ()->usuarioDao.insert(usuario)
        );
    }
    public LiveData<List<Usuario>> rDevolverTodosUsuarios(){
        return listado_usuarios;
    }

    public void rEliminarUsuario(Usuario usuario){
        AppDataBase.dbExecutor.execute(
                ()->usuarioDao.delete(usuario)
        );
    }

    public LiveData<Usuario> rDevolverUsuarioConId(String miId, String miPassword){
        return usuarioDao.getOne(miId, miPassword);
    }
}
