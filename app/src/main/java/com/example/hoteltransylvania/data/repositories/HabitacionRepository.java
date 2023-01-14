package com.example.hoteltransylvania.data.repositories;

/* El repositorio accede a la base de datos para recuperar el DAO,
tiene que tener los métodos necesarios para trabajar con el mismo*/

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.hoteltransylvania.data.AppDataBase;
import com.example.hoteltransylvania.data.dao.HabitacionDao;
import com.example.hoteltransylvania.data.entities.Habitacion;

import java.util.List;

public class HabitacionRepository {

    private HabitacionDao habitacionDao;

    private LiveData<List<Habitacion>> listado_habitaciones; //no hace falta pero es recomendable
    private Habitacion mihabitacion;

    public HabitacionRepository(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        habitacionDao = db.habitacionDao();
        listado_habitaciones = habitacionDao.getAll();
    }
    //Se añade un método por cada operación del DAO que deseemos realizar

    public void rInsertarHabitacion(Habitacion habitacion){
        AppDataBase.dbExecutor.execute(
                ()->habitacionDao.insert(habitacion)
        );
    }
    public LiveData<List<Habitacion>> rDevolverTodasHabitaciones(){
        return listado_habitaciones;
    }

    public void delete (Habitacion habitacion){
        AppDataBase.dbExecutor.execute(
                ()->habitacionDao.delete(habitacion)
        );
    }

    public LiveData<Habitacion> rDevolverHabitacionConId(String miId){
        return habitacionDao.getOne(miId);
    }

}
