package com.example.amrizalns.botic.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
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
import android.widget.Toast;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.models.Review;
import com.botic.coreapps.networks.RetrofitApi;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.ReviewAdapter;
import com.example.amrizalns.botic.recyclerViewHolder;
import com.example.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.List;

public class detail_content extends AppCompatActivity {

    private List<Review> mReviewList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReviewAdapter mAdapter;
    ImageView img;
    TextView name, loc, desc, cost, time_open, time_close;
    FloatingActionButton direction;
    Button button_review, button_share;

    int img_detail;
    String name_detail;
    String loc_detail;
    String desc_detail;
    String cost_detail;
    String timeopen_detail;
    String timeclose_detail;
    private ObjectItem objectItem;
    ProgressDialog dialog;

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
        button_share = (Button) findViewById(R.id.btn_share);

        Intent i = getIntent();
        if (i.hasExtra("object")) {
            objectItem = i.getParcelableExtra("object");
            img.setImageResource(R.mipmap.ic_botic);
            name.setText(objectItem.getName());
            loc.setText(objectItem.getAddress());
            cost.setText(objectItem.getPrice());
            time_open.setText(objectItem.getOpen());
            time_close.setText(objectItem.getClose());
            desc.setText(objectItem.getDescription());
            getReview(objectItem.getId());
        }

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(detail_content.this, directionActivity.class);
                startActivity(i);
            }
        });
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_review);
        mAdapter = new ReviewAdapter(mReviewList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

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
                                rb_review.setRating((float) a);
                                review(reviews, (int) rb_review.getRating());
                                dialogBox.dismiss();
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

        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, objectItem.getName());
                shareIntent.putExtra(Intent.EXTRA_TEXT, objectItem.getAddress());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });
    }

    private void review(String review, int rating) {
        RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken())
                .review(review, rating, objectItem.getId(), objectItem.getIdMenu())
                .enqueue(new PageCallback<Review>(detail_content.this) {
                    @Override
                    protected void onStart() {
                        dialog.show();
                    }

                    @Override
                    protected void onFinish() {
                        dialog.dismiss();
                    }

                    @Override
                    protected void onSuccess(Review data) {
                        getReview(objectItem.getId());
                        Toast.makeText(detail_content.this, "Berhasil Review", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onError(String message) {
                        super.onError(message);
                        Toast.makeText(detail_content.this, "Gagal Review", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onUnauthorized() {
                        SessionLogin.reset();
                        Intent intent = new Intent(detail_content.this, signIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
    }

    private void getReview(int id) {
        RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken())
                .getReview(id)
                .enqueue(new PageCallback<List<Review>>(detail_content.this) {
                    @Override
                    protected void onStart() {

                    }

                    @Override
                    protected void onFinish() {

                    }

                    @Override
                    protected void onSuccess(List<Review> data) {
                        mReviewList.clear();
                        mReviewList.addAll(data);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String message) {
                        super.onError(message);
                    }

                    @Override
                    protected void onUnauthorized() {
                        SessionLogin.reset();
                        Intent intent = new Intent(detail_content.this, signIn.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
