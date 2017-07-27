package com.bojonegorotic.amrizalns.botic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.model.Booking;

public class booking_gedung_finish extends AppCompatActivity{

    private TextView noIdentitas, nama, noHP, desGedung, jenisGedung, datePick, waktu;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_gedung_finish);

        noIdentitas = (TextView)findViewById(R.id.tv_noIdentitas);
        nama = (TextView) findViewById(R.id.tv_nama);
        noHP = (TextView)findViewById(R.id.tv_noHP);
        desGedung = (TextView)findViewById(R.id.tv_desGedung);
        jenisGedung = (TextView)findViewById(R.id.tv_jenisGedung);
        datePick = (TextView)findViewById(R.id.tv_tanggal);
        waktu = (TextView)findViewById(R.id.tv_waktu);

        mButton = (Button)findViewById(R.id.selesai_gedung);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(booking_gedung_finish.this, mainInterface.class);
                startActivity(intent);
            }
        });

        Booking mBooking = (Booking)getIntent().getSerializableExtra(booking_gedung.SER_KEY);
        noIdentitas.setText(mBooking.getNoIdentitas());
        nama.setText(mBooking.getNama());
        noHP.setText(mBooking.getNoHP());
        desGedung.setText(mBooking.getDesGedung());
        jenisGedung.setText(mBooking.getJenisGedung());
        datePick.setText(mBooking.getDatePick());
        waktu.setText(mBooking.getWaktu());



    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(booking_gedung_finish.this, mainInterface.class);
        startActivity(intent);
    }

}
