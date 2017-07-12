package com.example.amrizalns.botic.model;

/**
 * Created by user on 10/07/2017.
 */

public class Aktivitas {
    private String nama, tanggal;

    public Aktivitas() {
    }

    public Aktivitas(String nama, String tanggal) {
        this.nama = nama;
        this.tanggal = tanggal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
