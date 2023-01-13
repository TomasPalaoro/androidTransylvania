package com.example.hoteltransylvania.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.hoteltransylvania.data.entities.Reserva;

import java.util.List;

@Dao
public interface ReservaDao {

    @Insert
    public void insert(Reserva reserva);

    @Update
    public void update(Reserva reserva);

    @Delete
    public void delete(Reserva reserva);

    @Query("SELECT * FROM reservas ORDER BY id")
    public LiveData<List<Reserva>> getAll();

    @Query("SELECT * FROM reservas WHERE id = :miId")
    public LiveData<Reserva> getOne(String miId);

}
