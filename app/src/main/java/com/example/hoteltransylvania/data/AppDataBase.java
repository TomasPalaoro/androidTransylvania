package com.example.hoteltransylvania.data;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hoteltransylvania.data.dao.HabitacionDao;
import com.example.hoteltransylvania.data.dao.ReservaDao;
import com.example.hoteltransylvania.data.dao.UsuarioDao;
import com.example.hoteltransylvania.data.entities.Habitacion;
import com.example.hoteltransylvania.data.entities.Reserva;
import com.example.hoteltransylvania.data.entities.Usuario;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Usuario.class, Habitacion.class, Reserva.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract UsuarioDao usuarioDao();
    public abstract HabitacionDao habitacionDao();
    public abstract ReservaDao reservaDao();

    private static final String DATABASE_NAME = "transylvania";

    private static AppDataBase INSTANCE;

    private static final int THREADS = 4;
    public static final ExecutorService dbExecutor = Executors.newFixedThreadPool(THREADS);

    public static AppDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(), AppDataBase.class,
                                    DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
