package com.example.hoteltransylvania.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "habitaciones")
public class Habitacion {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name ="id")
    String id;

    @NonNull
    @ColumnInfo(name="numero_personas")
    Integer numero_personas;

    @ColumnInfo(name ="descrip")
    String descrip;

    @ColumnInfo(name ="precio")
    double precio;

    @ColumnInfo(name ="imagen")
    Integer imagen;

    public Habitacion(@NonNull String id, @NonNull Integer numero_personas, String descrip, double precio, Integer imagen) {
        this.id = id;
        this.numero_personas = numero_personas;
        this.descrip = descrip;
        this.precio = precio;
        this.imagen = imagen;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    @NonNull
    public Integer getNumero_personas() {
        return numero_personas;
    }

    public void setNumero_personas(@NonNull Integer numero_personas) {
        this.numero_personas = numero_personas;
    }

    public String getDescrip() {
        return descrip;
    }

    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }
}
