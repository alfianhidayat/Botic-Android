package com.example.amrizalns.botic.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.ReviewAdapter;
import com.example.amrizalns.botic.model.Review;
import com.example.amrizalns.botic.recyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class detail_content extends AppCompatActivity {

    private List<Review> mReviewList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReviewAdapter mAdapter;
    ImageView img;
    TextView name, loc, desc, cost, time_open, time_close;
    FloatingActionButton direction;
    Button button_review;

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
        button_review = (Button) findViewById(R.id.btn_review);

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

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_review);
        mAdapter = new ReviewAdapter(mReviewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        Review review = new Review("aaa", "aaaa");
        mReviewList.add(review);

        button_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflaterAndroid = LayoutInflater.from(detail_content.this);
                View mView = layoutInflaterAndroid.inflate(R.layout.input_data_review, null);
                AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(detail_content.this);
                alertDialogBuilderUserInput.setView(mView);
                final EditText reviewInput = (EditText) mView.findViewById(R.id.tambah_review);
                final RatingBar rb_review = (RatingBar) mView.findViewById(R.id.rb_review);
                alertDialogBuilderUserInput
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                String reviews = reviewInput.getText().toString();
                                String rating = String.valueOf(rb_review.getRating());
                                float a = Float.parseFloat(rating);
                                rb_review.setRating((float)a);
                                Review review = new Review(reviews, rating);
                                mReviewList.add(review);
                                mAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Batal",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {
                                        dialogBox.cancel();
                                    }
                                });

                AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
                alertDialogAndroid.show();
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
