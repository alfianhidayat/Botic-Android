package com.example.amrizalns.botic.model;

import java.io.Serializable;

/**
 * Created by user on 04/06/2017.
 */

public class Booking implements Serializable {
    private String noIdentitas, noHP, desGedung, jenisGedung, datePick, waktu;

    public Booking() {
    }

    public Booking(String noIdentitas, String noHP, String desGedung, String jenisGedung, String datePick, String waktu) {
        this.noIdentitas = noIdentitas;
        this.noHP = noHP;
        this.desGedung = desGedung;
        this.jenisGedung = jenisGedung;
        this.datePick = datePick;
        this.waktu = waktu;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public void setNoIdentitas(String noIdentitas) {
        this.noIdentitas = noIdentitas;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    public String getDesGedung() {
        return desGedung;
    }

    public void setDesGedung(String desGedung) {
        this.desGedung = desGedung;
    }

    public String getJenisGedung() {
        return jenisGedung;
    }

    public void setJenisGedung(String jenisGedung) {
        this.jenisGedung = jenisGedung;
    }

    public String getDatePick() {
        return datePick;
    }

    public void setDatePick(String datePick) {
        this.datePick = datePick;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
