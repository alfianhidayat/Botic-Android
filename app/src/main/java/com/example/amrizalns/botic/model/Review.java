package com.example.amrizalns.botic.model;

/**
 * Created by user on 12/07/2017.
 */

public class Review {
    private String nama, review;

    public Review() {
    }

    public Review(String nama, String review) {
        this.nama = nama;
        this.review = review;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
