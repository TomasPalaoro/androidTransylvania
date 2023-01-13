package com.example.hoteltransylvania.data.dao;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.hoteltransylvania.data.entities.Habitacion;

import java.util.List;

@Dao
public interface HabitacionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insert(Habitacion habitacion);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertBothUsers(Habitacion hab1, Habitacion hab2);

    @Update
    public void update(Habitacion habitacion);

    @Delete
    public void delete(Habitacion habitacion);

    @Query("SELECT * FROM habitaciones ORDER BY id")
    public LiveData<List<Habitacion>> getAll();

    @Query("SELECT * FROM habitaciones WHERE id = :miId")
    public LiveData<Habitacion> getOne(String miId);

    @Query("SELECT * FROM habitaciones WHERE precio BETWEEN :minPrecio AND :maxPrecio")
    public LiveData<List<Habitacion>> getAllPrices(int minPrecio, int maxPrecio);

    @Query("SELECT * FROM habitaciones WHERE descrip LIKE :search ")
    public LiveData<List<Habitacion>> findHabitacion(String search);

}