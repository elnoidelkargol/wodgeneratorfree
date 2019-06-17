package com.nappstic.wodselector;

import java.io.Serializable;

public class Result implements Serializable {
    public String _id;
    public String WodId;
    public String Fecha;
    public String Rounds;
    public String Time;
    public String Nombre;

    public Result(String _id, String wodId, String fecha, String rounds, String time, String nombre) {
        this._id = _id;
        WodId = wodId;
        Fecha = fecha;
        Rounds = rounds;
        Time = time;
        Nombre = nombre;
    }

    public Result() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWodId() {
        return WodId;
    }

    public void setWodId(String wodId) {
        WodId = wodId;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getRounds() {
        return Rounds;
    }

    public void setRounds(String rounds) {
        Rounds = rounds;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
