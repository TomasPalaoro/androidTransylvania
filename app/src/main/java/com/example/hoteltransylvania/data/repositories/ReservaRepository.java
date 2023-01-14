package com.example.hoteltransylvania.data.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.hoteltransylvania.data.AppDataBase;
import com.example.hoteltransylvania.data.dao.ReservaDao;
import com.example.hoteltransylvania.data.entities.Reserva;

import java.util.List;

public class ReservaRepository {
    private ReservaDao reservaDao;

    private LiveData<List<Reserva>> listado_reservas;

    public ReservaRepository(Context context){
        AppDataBase db = AppDataBase.getInstance(context);
        reservaDao = db.reservaDao();
        listado_reservas = reservaDao.getAll();
    }

    public void rInsertarReserva(Reserva reserva){
        AppDataBase.dbExecutor.execute(
                ()->reservaDao.insert(reserva)
        );
    }
    public LiveData<List<Reserva>> rDevolverTodasReservas(){
        return listado_reservas;
    }

    public void rEliminarReserva(Reserva reserva){
        AppDataBase.dbExecutor.execute(
                ()->reservaDao.delete(reserva)
        );
    }

    public LiveData<Reserva> rDevolverReservaConId(String miId){
        return reservaDao.getOne(miId);
    }
}
