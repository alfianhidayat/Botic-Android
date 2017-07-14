package com.example.amrizalns.botic.model;

/**
 * Created by user on 12/07/2017.
 */

public class CheckinData {
    private String nama, asal, umur;

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public CheckinData() {
    }

    public CheckinData(String nama, String asal) {
        this.nama = nama;
        this.asal = asal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }
}
