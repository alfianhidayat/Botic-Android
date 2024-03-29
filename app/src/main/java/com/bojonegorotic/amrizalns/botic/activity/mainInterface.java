package com.bojonegorotic.amrizalns.botic.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.botic.coreapps.models.User;
import com.botic.coreapps.networks.RetrofitApi;
import com.botic.coreapps.responses.BaseResponse;
import com.bojonegorotic.amrizalns.botic.BuildConfig;
import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.fragment.aboutbjn;
import com.bojonegorotic.amrizalns.botic.fragment.beranda;
import com.bojonegorotic.amrizalns.botic.fragment.booking;
import com.bojonegorotic.amrizalns.botic.fragment.event;
import com.bojonegorotic.amrizalns.botic.fragment.favorite;
import com.bojonegorotic.amrizalns.botic.fragment.kesehatan;
import com.bojonegorotic.amrizalns.botic.fragment.keuangan;
import com.bojonegorotic.amrizalns.botic.fragment.leisure;
import com.bojonegorotic.amrizalns.botic.fragment.pelayananpublik;
import com.bojonegorotic.amrizalns.botic.fragment.tempatIbadah;
import com.bojonegorotic.amrizalns.botic.utils.Constants;
import com.bojonegorotic.amrizalns.botic.utils.CustomPicasso;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;
import com.bojonegorotic.amrizalns.botic.utils.SharedPrefManager;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mainInterface extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private PopupWindow mPopupWindow;
    private CoordinatorLayout mCoordinatorLayout;
    Context mContext = this;
    private JSONObject response, profile_pic_data, profile_pic_url;
    private NavigationView mNavigationView;
    private TextView userName, userEmail;
    private CircleImageView mProfileImageView;
    private String mUsername, mEmail;

    SharedPrefManager sharedPrefManager;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.cor);

        configureSignIn();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = mNavigationView.getHeaderView(0);

        userName = (TextView) header.findViewById(R.id.name_profil);
        mProfileImageView = (CircleImageView) header.findViewById(R.id.userpic);
        userEmail = (TextView) header.findViewById(R.id.email_profil);
        getUserProfileGoogle();
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        //JSON Facebook
        Intent intent = getIntent();
        if (intent.hasExtra("jsondata")) {
            String jsondata = intent.getStringExtra("jsondata");
//            setNavigationHeader();
            setUserProfile(jsondata);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        if (Hawk.contains(Constants.SHARED_PREF_PROFILE)) {
            userEmail.setText(SessionLogin.getProfile().getEmail());
            userName.setText(SessionLogin.getProfile().getName());
        } else {
            getProfile();
        }
        loadFragment(R.id.nav_beranda);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mainInterface.this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure to exit Botic App ?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
//                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        switch (id) {
            case R.id.nav_logout:
                logoutFromFacebook();
                signOut();
                SessionLogin.reset();
                if (SessionLogin.isExist()) {
                    Intent intent = new Intent(mainInterface.this, signIn.class);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        loadFragment(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    //-----------User Profil Facebook-----------
    public void setUserProfile(String data) {
        try {
            response = new JSONObject(data);
            userEmail.setText(response.get("email").toString());
            userName.setText(response.get("name").toString());
            profile_pic_data = new JSONObject(response.get("picture").toString());
            profile_pic_url = new JSONObject(profile_pic_data.getString("data"));
            Picasso.with(this).load(profile_pic_url.getString("url")).into(mProfileImageView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getProfile() {
        RetrofitApi.getInstance(this).getApiService(SessionLogin.getAccessToken())
                .getProfile()
                .enqueue(new Callback<BaseResponse<User>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                        if (response.isSuccessful()) {
                            SessionLogin.saveProfile(response.body().getData());
                            userName.setText(SessionLogin.getProfile().getName());
                            userEmail.setText(SessionLogin.getProfile().getEmail());
                        } else if (response.code() == 401) {
                            Toast.makeText(mainInterface.this, R.string.failure_login, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                        Toast.makeText(mContext, "Terjadi kesalahan server !", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    //-----------End - User Profil Facebook-----------


    //-------------User Google Sign in----------------//
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void configureSignIn() {
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FacebookSdk.sdkInitialize(this);
    }

    private void getUserProfileGoogle() {
        sharedPrefManager = new SharedPrefManager(mContext);
        mUsername = sharedPrefManager.getName();
        mEmail = sharedPrefManager.getUserEmail();
        String uri = sharedPrefManager.getPhoto();
//        Uri mPhotoUri = Uri.parse(uri);

        userName.setText(mUsername);
        userEmail.setText(mEmail);
        CustomPicasso.getInstance(mContext)
                .load(uri)
                .placeholder(R.drawable.ic_account_circle) 
                .error(R.drawable.ic_account_circle)
                .into(mProfileImageView);
    }

    public void logoutFromFacebook() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(mainInterface.this, signIn.class);
        startActivity(intent);
    }

    private void signOut() {
        new SharedPrefManager(mContext).clear();

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        Intent intent = new Intent(mainInterface.this, signIn.class);
                        startActivity(intent);
                    }
                }
        );
//        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(@NonNull Status status) {
//                        Intent intent = new Intent(mainInterface.this, signIn.class);
//                        startActivity(intent);
//                    }
//                }
//        );
    }
    //-----------End - User Google Sign in------------//


    public void loadFragment(int id) {
        Fragment f = null;

        if (id == R.id.nav_beranda) {
            f = new beranda();
        } else if (id == R.id.nav_aktivitas) {
            f = new favorite();
        } else if (id == R.id.nav_layanan) {
            f = new pelayananpublik();
        } else if (id == R.id.nav_tmpIbadah) {
            f = new tempatIbadah();
        } else if (id == R.id.nav_keuangan) {
            f = new keuangan();
        } else if (id == R.id.nav_kesehatan) {
            f = new kesehatan();
        } else if (id == R.id.nav_leisure) {
            f = new leisure();
        } else if (id == R.id.nav_aboutBjn) {
            f = new aboutbjn();
        } else if (id == R.id.nav_event) {
            f = new event();
        } else if (id == R.id.nav_booking) {
            f = new booking();
        } else if (id == R.id.nav_aboutApp) {
            showPopUp();
        } else if (id == R.id.nav_logout) {
            logoutFromFacebook();
            signOut();
            SessionLogin.reset();
            if (SessionLogin.isExist()) {
                Intent intent = new Intent(mainInterface.this, signIn.class);
                startActivity(intent);
            }
        }


        if (f != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, f);
            ft.commit();
        }
    }


    private void showPopUp() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);

        View customView = inflater.inflate(R.layout.popup, null);
        mPopupWindow = new PopupWindow(
                customView,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        if (Build.VERSION.SDK_INT >= 21) {
            mPopupWindow.setElevation(5.0f);
        }

        ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
        TextView tvVersion = (TextView) customView.findViewById(R.id.tv_version);

        tvVersion.setText("version " + BuildConfig.VERSION_NAME);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow.showAtLocation(mCoordinatorLayout, Gravity.CENTER, 0, 0);
    }
}
