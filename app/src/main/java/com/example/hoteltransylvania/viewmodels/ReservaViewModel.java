package com.example.hoteltransylvania.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.data.entities.Reserva;
import com.example.hoteltransylvania.data.repositories.ReservaRepository;

import java.util.List;

public class ReservaViewModel extends AndroidViewModel {
    private final ReservaRepository reservaRepository;
    private final LiveData<List<Reserva>> listadoReservas;

    public ReservaViewModel(@NonNull Application application) {
        super(application);
        this.reservaRepository = new ReservaRepository(application);
        this.listadoReservas = reservaRepository.rDevolverTodasReservas();
    }

    public LiveData<List<Reserva>> devolverTodosReservas(){
        return listadoReservas;
    }

    public void insertarReserva(Reserva reserva){
        reservaRepository.rInsertarReserva(reserva);
    }

    public void eliminarReserva(Reserva reserva){
        reservaRepository.rEliminarReserva(reserva);
    }

    public LiveData<Reserva> devolverReservaConId(String miD){
        return reservaRepository.rDevolverReservaConId(miD);
    }
    public LiveData<List<Reserva>> devolverReservasWhere(String idUsuario){
        return reservaRepository.rDevolverReservasWhere(idUsuario);
    }
}
