package com.example.amrizalns.botic.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.botic.coreapps.models.Token;
import com.botic.coreapps.models.User;
import com.botic.coreapps.networks.RetrofitApi;
import com.botic.coreapps.responses.BaseResponse;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.utils.SessionLogin;
import com.example.amrizalns.botic.utils.SharedPrefManager;
import com.example.amrizalns.botic.utils.Utils;
import com.example.amrizalns.botic.viewholder.JSONParser;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class signIn extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = "SignIn";
    private static final int RC_SIGN_IN = 908;

    private GoogleSignInOptions mGoogleSignInOptions;
    private GoogleApiClient mGoogleApiClient;
    private SignInButton mSignInButton;
    private Intent mSigninIntent;
    private String idToken;
    public SharedPrefManager sharedPrefManager;
    private final Context mContext = this;

    private LoginButton mFacebook;
    private AccessTokenTracker mAccessTokenTracker;
    private ProfileTracker mProfileTracker;

    private Button mSingIn;
    private TextView mRegis;
    private EditText mEmail, mPassword;
    private ProgressDialog mProgressDialog;
    private JSONParser mJSONParser = new JSONParser();
    private static String url = "";
    private static final String TAG_SUCCESS = "success";
    private CallbackManager mCallbackManager;

    private String name, email;
    private String photo;
    private Uri photoUri;
    private Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_in);

        //API Facebook
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        mCallbackManager = CallbackManager.Factory.create();
        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                       AccessToken currentAccessToken) {
                if (currentAccessToken == null) {
                    updateUIfbButton(false);
                }
            }
        };

        mAccessTokenTracker.startTracking();

        //Login Button API Facebook
        mFacebook = (LoginButton) findViewById(R.id.login_button);
        mFacebook.setReadPermissions("email");
        signInButtonFacebook(mFacebook);

        //Sign in Button API Google
        mSignInButton = (SignInButton) findViewById(R.id.sign_in_g_button);
        mSignInButton.setSize(SignInButton.SIZE_WIDE);
        mSignInButton.setColorScheme(SignInButton.COLOR_AUTO);
        mSignInButton.setOnClickListener(this);

        configureSignIn();

        //Sign in Application
        mEmail = (EditText) findViewById(R.id.field_email);
        mPassword = (EditText) findViewById(R.id.field_password);
        mSingIn = (Button) findViewById(R.id.sign_in_button);
        mSingIn.setOnClickListener(this);
        mRegis = (TextView) findViewById(R.id.link_regist);
        mRegis.setOnClickListener(this);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading...");
        i = new Intent(signIn.this, mainInterface.class);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        Utils utils = new Utils(this);
        switch (v.getId()) {
            case R.id.sign_in_button:
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if (email.length() == 0 && password.length() == 0) {
                    mEmail.setError("Required");
                    mPassword.setError("Required");
                } else {
                    mProgressDialog.show();
                    RetrofitApi.getInstance(this).getApiService("")
                            .login("password",
                                    "2",
                                    "Ypsems1mc9lfAMSY3QAacVl7mVSE3FTuKk5s3n8S",
                                    mEmail.getText().toString(),
                                    mPassword.getText().toString(), "")
                            .enqueue(new Callback<Token>() {
                                @Override
                                public void onResponse(Call<Token> call, Response<Token> response) {
//                                    getProfile();
                                    if (response.isSuccessful()) {
                                        SessionLogin.saveAccessToken(response.body());
                                        Toast.makeText(signIn.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(signIn.this, mainInterface.class);
                                        startActivity(i);
                                    } else if (response.code() == 401) {
                                        Toast.makeText(signIn.this, R.string.failure_login, Toast.LENGTH_SHORT).show();
                                    }
                                    mProgressDialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<Token> call, Throwable t) {
                                    Log.e("Error", t.getLocalizedMessage());
                                    Toast.makeText(mContext, "Terjadi kesalahan server !", Toast.LENGTH_SHORT).show();
                                    mProgressDialog.dismiss();
                                }
                            });
                }
                break;
            case R.id.link_regist:
                i = new Intent(signIn.this, signUp.class);
                startActivity(i);
                break;
            case R.id.sign_in_g_button:
                if (utils.isNetworkAvailable()) {
                    signIn();
                } else {
                    Toast.makeText(signIn.this, "Oops! no internet connection!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
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
                            Toast.makeText(signIn.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(signIn.this, mainInterface.class);
                            startActivity(i);
                        } else if (response.code() == 401) {
                            Toast.makeText(signIn.this, R.string.failure_login, Toast.LENGTH_SHORT).show();
                        }
                        mProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                        Toast.makeText(mContext, "Terjadi kesalahan server !", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }
                });
    }

    //-------------API Facebook-------------------
//    public void facebookLogin(){
//        mSharedPreferences = getPreferences(MODE_PRIVATE);
//        String access_token = mSharedPreferences.getString("access_token", null);
//
//        long expires = mSharedPreferences.getLong("access_expires", 0);
//
//        if (access_token != null){
//            loginResult.setAccessToken(access_token);
//        }
//    }

    private void loginSocialite(String name, String email, String provider) {
        mProgressDialog.show();
        RetrofitApi.getInstance(this).getApiService("")
                .loginSocialite(name, email, provider)
                .enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if (response.isSuccessful()) {
                            SessionLogin.saveAccessToken(response.body());
                            Toast.makeText(signIn.this, R.string.success_login, Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        } else if (response.code() == 401) {
                            Toast.makeText(signIn.this, R.string.failure_login, Toast.LENGTH_SHORT).show();
                        }
                        mProgressDialog.dismiss();
//                        getProfile();
                    }

                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                        Toast.makeText(mContext, "Terjadi kesalahan server !", Toast.LENGTH_SHORT).show();
                        mProgressDialog.dismiss();
                    }
                });
    }

    protected void getUserInfo(LoginResult loginResult) {

        GraphRequest dataRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                if (object != null) {

                }
//                Intent i = new Intent(signIn.this, mainInterface.class);
                i = new Intent(signIn.this, mainInterface.class);
                i.putExtra("jsondata", object.toString());
//                startActivity(i);
                try {
                    JSONObject res = new JSONObject(object.toString());
                    String name = res.get("name").toString();
                    String email = res.get("email").toString();
                    Toast.makeText(mContext, name + " : " + email, Toast.LENGTH_SHORT).show();
                    loginSocialite(name, email, "facebook");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle permission = new Bundle();
        permission.putString("fields", "id,name,email,picture");
        dataRequest.setParameters(permission);
        dataRequest.executeAsync();
    }

    private void signInButtonFacebook(final LoginButton loginButton) {
        mFacebook.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserInfo(loginResult);
                updateUIfbButton(true);
            }

            @Override
            public void onCancel() {
                Log.d("LoginFacebook", "Canceled");
                AlertDialog alertDialog = new AlertDialog.Builder(signIn.this).create();
                alertDialog.setTitle("Pemberitahuan");
                alertDialog.setMessage("Masuk Facebook Gagal");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(signIn.class.getCanonicalName(), error.getMessage());
            }
        });
    }

    private void updateUIfbButton(boolean isSignedIn) {
        if (isSignedIn == true) {
            mEmail.setVisibility(View.INVISIBLE);
            mPassword.setVisibility(View.INVISIBLE);
            mSingIn.setVisibility(View.INVISIBLE);
            mFacebook.setVisibility(View.VISIBLE);
            mSignInButton.setVisibility(View.INVISIBLE);
        } else {
            mEmail.setVisibility(View.VISIBLE);
            mPassword.setVisibility(View.VISIBLE);
            mSingIn.setVisibility(View.VISIBLE);
            mFacebook.setVisibility(View.VISIBLE);
            mSignInButton.setVisibility(View.VISIBLE);
        }
    }
    //-------------End - API Facebook-------------------


    //----------------API Google+-----------------------//
    public void configureSignIn() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
        mGoogleApiClient.connect();
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnected(Bundle bundle) {

    }
//    private void signOut() {
//        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
//                new ResultCallback<Status>() {
//                    @Override
//                    public void onResult(Status status) {
//                        updateUI(false);
//                    }
//                });
//    }
//    private void handleSignInResult(GoogleSignInResult result) {
//        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
//        if (result.isSuccess()) {
//            GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
//
//            Log.e(TAG, "display name: " + googleSignInAccount.getDisplayName());
//
//            String personName = googleSignInAccount.getDisplayName();
//            String personPhotoUrl = googleSignInAccount.getPhotoUrl().toString();
//            String email = googleSignInAccount.getEmail();
//
//            Log.e(TAG, "Name: " + personName + ", email: " + email
//                    + ", Image: " + personPhotoUrl);
//
//            mSigninIntent = new Intent(this, mainInterface.class);
//            mSigninIntent.putExtra("name", personName);
//            mSigninIntent.putExtra("email", email);
//            mSigninIntent.putExtra("photo", personPhotoUrl);
////
////            txtName.setText(personName);
////            txtEmail.setText(email);
////            Glide.with(getApplicationContext()).load(personPhotoUrl)
////                    .thumbnail(0.5f)
////                    .crossFade()
////                    .diskCacheStrategy(DiskCacheStrategy.ALL)
////                    .into(imgProfilePic);
//
//            updateUI(true);
//        } else {
//            updateUI(false);
//        }
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//        Log.d(TAG, "onConnectionFailed:" + connectionResult);
//    }
//
//    private void showProgressDialog() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this);
//            mProgressDialog.setMessage(getString(R.string.loading));
//            mProgressDialog.setIndeterminate(true);
//        }
//
//        mProgressDialog.show();
//    }

    //----------------End - API Google+-----------------//SSS


    @Override
    protected void onStart() {
        super.onStart();
        FacebookSdk.sdkInitialize(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppEventsLogger.activateApp(this);
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAccessTokenTracker.stopTracking();
//        mProfileTracker.stopTracking();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(signIn.this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        Log.e("data", data.toString());

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();

                idToken = account.getIdToken();

                name = account.getDisplayName();
                email = account.getEmail();
                photoUri = account.getPhotoUrl();
                photo = photoUri.toString();

                // Save Data to SharedPreference
                sharedPrefManager = new SharedPrefManager(mContext);
                sharedPrefManager.saveIsLoggedIn(mContext, true);

                sharedPrefManager.saveEmail(mContext, email);
                sharedPrefManager.saveName(mContext, name);
                sharedPrefManager.savePhoto(mContext, photo);

                sharedPrefManager.saveToken(mContext, idToken);
                sharedPrefManager.saveIsLoggedIn(mContext, true);

                loginSocialite(name, email, "google");
            } else {
                Log.e(TAG, "Login Google Unsuccessful. ");
                Toast.makeText(this, "Login Google Unsuccessful", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
