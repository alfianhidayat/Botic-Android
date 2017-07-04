package com.example.amrizalns.botic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.model.Booking;

public class booking_gedung_finish extends AppCompatActivity{

    private TextView noIdentitas, noHP, desGedung, jenisGedung, datePick, waktu;
    private Button sk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_gedung_finish);

        noIdentitas = (TextView)findViewById(R.id.tv_noIdentitas);
        noHP = (TextView)findViewById(R.id.tv_noHP);
        desGedung = (TextView)findViewById(R.id.tv_desGedung);
        jenisGedung = (TextView)findViewById(R.id.tv_jenisGedung);
        datePick = (TextView)findViewById(R.id.tv_tanggal);
        waktu = (TextView)findViewById(R.id.tv_waktu);

        sk = (Button)findViewById(R.id.sk);
        sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(booking_gedung_finish.this, popUp_sk_gedung.class);
                startActivity(intent);
            }
        });


        Booking mBooking = (Booking)getIntent().getSerializableExtra(booking_gedung.SER_KEY);
        noIdentitas.setText(mBooking.getNoIdentitas());
        noHP.setText(mBooking.getNoHP());
        desGedung.setText(mBooking.getDesGedung());
        jenisGedung.setText(mBooking.getJenisGedung());
        datePick.setText(mBooking.getDatePick());
        waktu.setText(mBooking.getWaktu());



    }


}
