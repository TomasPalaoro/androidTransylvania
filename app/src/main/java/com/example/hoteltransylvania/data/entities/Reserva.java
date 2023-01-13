package com.example.hoteltransylvania.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reservas", foreignKeys = {
        @ForeignKey(
                entity = Usuario.class,
                parentColumns = "email",
                childColumns = "idUsuario"
        ),
        @ForeignKey(
                entity = Habitacion.class,
                parentColumns = "id",
                childColumns = "idHabitacion"
        )
})
public class Reserva {
    @PrimaryKey(autoGenerate = true) //autoincremental
    @NonNull
    @ColumnInfo(name ="id")
    int id;

    @NonNull
    @ColumnInfo(name ="fecha_entrada")
    String fecha_entrada;

    @NonNull
    @ColumnInfo(name ="fecha_salida")
    String fecha_salida;

    @NonNull
    @ColumnInfo(name ="idUsuario")
    String idUsuario;

    @NonNull
    @ColumnInfo(name ="idHabitacion")
    String idHabitacion;

    public Reserva(@NonNull String fecha_entrada, @NonNull String fecha_salida, @NonNull String idUsuario, @NonNull String idHabitacion) {
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.idUsuario = idUsuario;
        this.idHabitacion = idHabitacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(@NonNull String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    @NonNull
    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(@NonNull String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    @NonNull
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(@NonNull String idUsuario) {
        this.idUsuario = idUsuario;
    }

    @NonNull
    public String getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(@NonNull String idHabitacion) {
        this.idHabitacion = idHabitacion;
    }
}
