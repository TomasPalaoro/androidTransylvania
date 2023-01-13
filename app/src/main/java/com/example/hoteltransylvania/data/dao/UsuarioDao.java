package com.example.hoteltransylvania.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.hoteltransylvania.data.entities.Usuario;

import java.util.List;

@Dao
public interface UsuarioDao {

    @Insert
    public void insert(Usuario usuario);

    @Update
    public void update(Usuario usuario);

    @Delete
    public void delete(Usuario usuario);

    @Query("SELECT * FROM usuarios ORDER BY email")
    public LiveData<List<Usuario>> getAll();

    @Query("SELECT * FROM usuarios WHERE email = :miId AND password = :miPassword")
    public LiveData<Usuario> getOne(String miId, String miPassword);

}
