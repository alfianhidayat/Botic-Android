package com.example.amrizalns.botic.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.networks.RetrofitApi;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.utils.SessionLogin;
import com.example.amrizalns.botic.viewholder.JSONParser;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class signUp extends AppCompatActivity {

    private ProgressDialog pDialog;
    private JSONParser mJSONParser = new JSONParser();
    private static String url = "";
    private static final String TAG_SUCCESS = "success";
    private EditText inputName, inputEmail, inputPass, inputConfPass;
    private Button regisButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inputName = (EditText) findViewById(R.id.field_signUp_name);
        inputEmail = (EditText) findViewById(R.id.field_signUp_email);
        inputPass = (EditText) findViewById(R.id.field_signUp_password);
        inputConfPass = (EditText) findViewById(R.id.field_signUp_confirmPass);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        regisButton = (Button) findViewById(R.id.sign_up_button);
        regisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String pass = inputPass.getText().toString();
                String confpass = inputConfPass.getText().toString();

                if (nama.length() == 0 && email.length()== 0 && pass.length() == 0 && confpass.length() == 0){
                    inputName.setError("Required");
                    inputEmail.setError("Required");
                    inputPass.setError("Required");
                    inputConfPass.setError("Required");

                } else if (inputPass.getText().toString().equals(inputConfPass.getText().toString()))
                    RetrofitApi.getInstance(signUp.this).getApiService(SessionLogin.getAccessToken())
                            .register(
                                    inputName.getText().toString(),
                                    inputEmail.getText().toString(),
                                    inputPass.getText().toString()
                            ).enqueue(new PageCallback<Object>(signUp.this) {
                        @Override
                        protected void onStart() {
                            pDialog.show();
                        }

                        @Override
                        protected void onFinish() {
                            pDialog.dismiss();
                        }

                        @Override
                        protected void onSuccess(Object data) {
                            Toast.makeText(signUp.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), signIn.class);
                            startActivity(i);
                        }

                        @Override
                        protected void onError(String message) {
                            super.onError(message);
                            Toast.makeText(signUp.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                else
                    Toast.makeText(signUp.this, "Confirm Password tidak sama !", Toast.LENGTH_SHORT).show();
//                new Registrasi(inputName.getText().toString(),inputEmail.getText().toString(),inputPass.getText().toString(),inputConfPass.getText().toString());
            }
        });
    }

    private class Registrasi extends AsyncTask<String, String, String> {
        String nama, email, password, confpassword;

        public Registrasi(String nama, String email, String password, String confpassword) {
            this.nama = nama;
            this.email = email;
            this.password = password;
            this.confpassword = confpassword;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(signUp.this);
            pDialog.setMessage("Membuat Akun...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            final List<NameValuePair> param = new ArrayList<>();
            param.add(new BasicNameValuePair("nama", nama));
            param.add(new BasicNameValuePair("email", email));
            param.add(new BasicNameValuePair("password", password));
            param.add(new BasicNameValuePair("confpassword", confpassword));

            JSONObject jsonObject = mJSONParser.makeHttpRequest(url, "POST", param);

            Log.d("Membuat Respon", jsonObject.toString());

            try {
                int success = jsonObject.getInt(TAG_SUCCESS);
                if (success == 1) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(signUp.this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), signIn.class);
                            startActivity(i);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(signUp.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pDialog.dismiss();
        }
    }
}
