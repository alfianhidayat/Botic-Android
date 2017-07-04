package com.example.amrizalns.botic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.amrizalns.botic.R;

/**
 * Created by amrizalns on 6/6/17.
 */

public class popUp_sk_gedung extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_up_sk_gedung);

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//
//        getWindow().setLayout((int)(width*.8), (int) (height*.6));

        Button btn = (Button) findViewById(R.id.sk_agree);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(popUp_sk_gedung.this, booking_gedung_finish.class);
                startActivity(i);
            }
        });
    }
}
