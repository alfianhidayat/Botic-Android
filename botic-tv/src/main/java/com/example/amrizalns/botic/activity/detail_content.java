package com.example.amrizalns.botic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amrizalns.botic.R;

public class detail_content extends AppCompatActivity {

    ImageView img;
    TextView name, loc, desc, cost, time_open, time_close;
    FloatingActionButton direction;

    int img_detail;
    String name_detail;
    String loc_detail;
    String desc_detail;
    String cost_detail;
    String timeopen_detail;
    String timeclose_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView) findViewById(R.id.detail_img);
        name = (TextView) findViewById(R.id.detail_name);
        loc = (TextView) findViewById(R.id.detail_loc);
        desc = (TextView) findViewById(R.id.detail_desc);
        cost = (TextView) findViewById(R.id.detail_cost);
        time_open = (TextView) findViewById(R.id.detail_time_open);
        time_close = (TextView) findViewById(R.id.detail_time_close);
        direction = (FloatingActionButton) findViewById(R.id.find_location);

        Intent i = getIntent();
        img_detail = i.getIntExtra("img", 0);
        img.setImageResource(img_detail);

        name_detail = i.getStringExtra("name");
        name.setText(name_detail);

        loc_detail = i.getStringExtra("loc");
        loc.setText(loc_detail);

        cost_detail = i.getStringExtra("cost");
        cost.setText(cost_detail);

        timeopen_detail = i.getStringExtra("time_open");
        time_open.setText(timeopen_detail);

        timeclose_detail = i.getStringExtra("time_close");
        time_close.setText(timeclose_detail);

        desc_detail = i.getStringExtra("desc");
        desc.setText(desc_detail);

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(detail_content.this, directionActivity.class);
                startActivity(i);
            }
        });

    }
}
