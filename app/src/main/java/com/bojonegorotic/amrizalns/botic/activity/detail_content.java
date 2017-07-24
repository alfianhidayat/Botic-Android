package com.bojonegorotic.amrizalns.botic.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bojonegorotic.amrizalns.botic.utils.Utils;
import com.botic.coreapps.AppsCore;
import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.models.Picture;
import com.botic.coreapps.models.Review;
import com.botic.coreapps.networks.RetrofitApi;
import com.bojonegorotic.amrizalns.botic.ImgViewPageAdapter;
import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.ReviewAdapter;
import com.bojonegorotic.amrizalns.botic.helper.RealmController;
import com.bojonegorotic.amrizalns.botic.utils.CustomPicasso;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;

public class detail_content extends AppCompatActivity {

    private List<Review> mReviewList = new ArrayList<>();
    private List<Picture> mPictureList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ReviewAdapter mAdapter;
    ImageView img, button_share, button_review, fav;
    TextView name, loc, desc, cost, time_open, time_close;
    FloatingActionButton direction;
    private Realm realm;
    int img_detail;
    String name_detail;
    String loc_detail;
    String desc_detail;
    String cost_detail;
    String timeopen_detail;
    String timeclose_detail;
    private ObjectItem objectItem;
    ProgressDialog dialog;
    boolean isChecked = false;
    ViewPager mViewPager;
    LinearLayout slider;
    private int imgcount;
    private ImageView[] img_content;
    ImgViewPageAdapter viewPagerAdapter;
    private int[] layouts = new int[]{
            R.drawable.banner_boticapps_1,
            R.drawable.banner_boticapps_2,
            R.drawable.banner_boticapps_3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.realm = RealmController.with(this).getRealm();
        setContentView(R.layout.activity_detail_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        img = (ImageView) findViewById(R.id.iv_photo);
        name = (TextView) findViewById(R.id.detail_name);
        loc = (TextView) findViewById(R.id.detail_loc);
        desc = (TextView) findViewById(R.id.detail_desc);
        cost = (TextView) findViewById(R.id.detail_cost);
        time_open = (TextView) findViewById(R.id.detail_time_open);
        time_close = (TextView) findViewById(R.id.detail_time_close);
        direction = (FloatingActionButton) findViewById(R.id.find_location);
        button_review = (ImageView) findViewById(R.id.btn_review);
        button_share = (ImageView) findViewById(R.id.btn_share);
        fav = (ImageView) findViewById(R.id.btn_fav);

        mViewPager = (ViewPager) findViewById(R.id.vp_content);
        slider = (LinearLayout) findViewById(R.id.sliderContent);

        Intent i = getIntent();
        if (i.hasExtra("object")) {
            objectItem = i.getParcelableExtra("object");
//            img.setImageResource(R.mipmap.ic_botic);
            name.setText(objectItem.getName());
            loc.setText(objectItem.getAddress());
            cost.setText(objectItem.getPrice());
            time_open.setText(objectItem.getOpen());
            time_close.setText(objectItem.getClose());
            desc.setText(objectItem.getDescription());
            getReview(objectItem.getId(), objectItem.getIdMenu());
            getPicture(objectItem.getId(), objectItem.getIdMenu());
        }

        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                directionActivity.start(detail_content.this, objectItem);
                String geoUri = "http://maps.google.com/maps?q=loc:" + objectItem.getLat() + "," + objectItem.getLng() + " (" + objectItem.getName() + ")";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                startActivity(intent);
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
                        .setNegativeButton(R.string.batal_dContent,
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

        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!isChecked);
            }
        });

        button_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, objectItem.getName() + "\n" + objectItem.getAddress());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }
        });

        com.bojonegorotic.amrizalns.botic.model.ObjectItem item = RealmController.with(this).getObject(objectItem.getId(), objectItem.getIdMenu());
        if (item != null) {
            fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
            isChecked = true;
        }
    }

    private void slider() {
        imgcount = viewPagerAdapter.getCount();
        img_content = new ImageView[imgcount];

        for (int i = 0; i < imgcount; i++) {
            img_content[i] = new ImageView(this);
            img_content[i].setImageDrawable(getResources().getDrawable(R.drawable.nonactive_dots));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(4, 0, 4, 0);
            slider.addView(img_content[i], params);
        }
        img_content[0].setImageDrawable(getResources().getDrawable(R.drawable.actived_dots));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < imgcount; i++) {
                    img_content[i].setImageDrawable(getResources().getDrawable(R.drawable.nonactive_dots));
                }
                img_content[position].setImageDrawable(getResources().getDrawable(R.drawable.actived_dots));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void review(String review, int rating) {
        RetrofitApi.getInstance(this).getApiService(SessionLogin.getAccessToken())
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
                        getReview(objectItem.getId(), objectItem.getIdMenu());
                        Toast.makeText(detail_content.this, R.string.suc_review, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected void onError(String message) {
                        super.onError(message);
                        Toast.makeText(detail_content.this, R.string.fail_review, Toast.LENGTH_SHORT).show();
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

    private void getReview(int id, int idMenu) {
        RetrofitApi.getInstance(this).getApiService(SessionLogin.getAccessToken())
                .getReview(id, idMenu)
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

    private void getPicture(int id, int idMenu) {
        RetrofitApi.getInstance(this).getApiService(SessionLogin.getAccessToken())
                .getPicture(id, idMenu)
                .enqueue(new PageCallback<List<Picture>>(detail_content.this) {
                    @Override
                    protected void onStart() {

                    }

                    @Override
                    protected void onFinish() {

                    }

                    @Override
                    protected void onSuccess(List<Picture> data) {
                        mPictureList.clear();
                        mPictureList.addAll(data);
                        mAdapter.notifyDataSetChanged();
                        viewPagerAdapter = new ImgViewPageAdapter(detail_content.this, layouts);
                        viewPagerAdapter.setPictureList(mPictureList);
                        mViewPager.setAdapter(viewPagerAdapter);
                        if (mPictureList.size() != 0) {
                            slider();
                            img.setVisibility(View.GONE);
                        } else {
                            CustomPicasso.getInstance(detail_content.this).load(AppsCore.BASE_URL + "image/" + ((mPictureList.size() == 0) ? R.drawable.ic_no_photo : mPictureList.get(0).getOriginalFilename()))
                                    .placeholder(R.drawable.ic_no_photo)
                                    .error(R.drawable.ic_no_photo)
                                    .into(img);
                        }
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

    private boolean isChecked() {
        return isChecked;
    }

    private void setChecked(boolean checked) {
        isChecked = checked;
        if (isChecked()) {
            fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
            com.bojonegorotic.amrizalns.botic.model.ObjectItem object = new com.bojonegorotic.amrizalns.botic.model.ObjectItem();
            object.setId((int) (objectItem.getId() + System.currentTimeMillis()));
            object.setIdObject(objectItem.getId());
            object.setPhone(objectItem.getPhone());
            object.setAddress(objectItem.getAddress());
            object.setClose(objectItem.getClose());
            object.setDescription(objectItem.getDescription());
            object.setIdCategory(objectItem.getIdCategory());
            object.setRating(objectItem.getRating());
            object.setIdMenu(objectItem.getIdMenu());
            object.setOpen(objectItem.getOpen());
            object.setName(objectItem.getName());
            realm.beginTransaction();
            realm.copyToRealm(object);
            realm.commitTransaction();
            Toast.makeText(this, R.string.favorite_toast, Toast.LENGTH_SHORT).show();
        } else {
            fav.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border));
            RealmController.with(this).remove(objectItem.getId(), objectItem.getIdMenu());
            Toast.makeText(this, R.string.favorite_toast_remove, Toast.LENGTH_SHORT).show();
        }
    }
}
