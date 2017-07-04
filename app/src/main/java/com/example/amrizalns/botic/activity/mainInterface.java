package com.example.amrizalns.botic.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.fragment.beranda;
import com.example.amrizalns.botic.fragment.booking;
import com.example.amrizalns.botic.utils.SharedPrefManager;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class mainInterface extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    Context mContext = this;
    private JSONObject response, profile_pic_data, profile_pic_url;
    private NavigationView mNavigationView;
    private TextView userName, userEmail;
    private ImageView userPic;
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

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = mNavigationView.getHeaderView(0);

        userName = (TextView) header.findViewById(R.id.name_profil);
        mProfileImageView = (CircleImageView) header.findViewById(R.id.userpic);
        userEmail = (TextView) header.findViewById(R.id.email_profil);

        Intent intent = getIntent();
        //JSON Facebook
        String jsondata = intent.getStringExtra("jsondata");
//        setNavigationHeader();
        setUserProfile(jsondata);

        configureSignIn();
        getUserProfileGoogle();

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
//

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
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_interface, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
//    public void setNavigationHeader() {
//        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
//        View header = LayoutInflater.from(this).inflate(R.layout.nav_header_main_interface, null);
//        mNavigationView.addHeaderView(header);
//        userName = (TextView) header.findViewById(R.id.name_profil);
//        userPic = (ImageView) header.findViewById(R.id.userpic);
//        userEmail = (TextView) header.findViewById(R.id.email_profil);
//    }

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

    private void getUserProfileGoogle() {
        sharedPrefManager = new SharedPrefManager(mContext);
        mUsername = sharedPrefManager.getName();
        mEmail = sharedPrefManager.getUserEmail();
        String uri = sharedPrefManager.getPhoto();
//        Uri mPhotoUri = Uri.parse(uri);

        userName.setText(mUsername);
        userEmail.setText(mEmail);

        Picasso.with(mContext)
                .load(uri)
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(mProfileImageView);
    }

    public void logoutFromFacebook(){
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


    public void loadFragment(int id){
        Fragment f = null;

        if (id == R.id.nav_beranda) {
            f = new beranda();
        } else if (id == R.id.nav_aktivitas) {

        } else if (id == R.id.nav_layanan) {

        } else if (id == R.id.nav_tmpIbadah) {

        } else if (id == R.id.nav_keuangan) {

        } else if (id == R.id.nav_transportasi) {

        } else if (id == R.id.nav_aboutBjn) {

        } else if (id == R.id.nav_event) {

        } else if (id == R.id.nav_booking) {
            f = new booking();

        } else if (id == R.id.nav_aboutApp) {

        } else if (id == R.id.nav_logout) {
            logoutFromFacebook();
            signOut();
        }

        if (f != null){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, f);
            ft.commit();
        }
    }
}
