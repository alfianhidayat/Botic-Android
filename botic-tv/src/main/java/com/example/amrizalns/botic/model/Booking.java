package com.example.amrizalns.botic.model;

import java.io.Serializable;

/**
 * Created by user on 04/06/2017.
 */

public class Booking implements Serializable {
    private String noIdentitas, nama, jenisIdentitas, noHP, desGedung, jenisGedung, datePick, waktu;

    public Booking() {
    }

    public Booking(String noIdentitas, String nama, String jenisIdentitas, String noHP, String desGedung, String jenisGedung, String datePick, String waktu) {
        this.noIdentitas = noIdentitas;
        this.nama = nama;
        this.jenisIdentitas = jenisIdentitas;
        this.noHP = noHP;
        this.desGedung = desGedung;
        this.jenisGedung = jenisGedung;
        this.datePick = datePick;
        this.waktu = waktu;
    }

    public String getNoIdentitas() {
        return noIdentitas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisIdentitas() {
        return jenisIdentitas;
    }

    public void setJenisIdentitas(String jenisIdentitas) {
        this.jenisIdentitas = jenisIdentitas;
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
