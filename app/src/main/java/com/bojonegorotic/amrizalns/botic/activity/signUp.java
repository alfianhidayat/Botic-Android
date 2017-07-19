package com.bojonegorotic.amrizalns.botic.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.networks.RetrofitApi;
import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;
import com.bojonegorotic.amrizalns.botic.viewholder.JSONParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signUp extends AppCompatActivity {

    private ProgressDialog pDialog;
    private JSONParser mJSONParser = new JSONParser();
    private static String url = "";
    private static final String TAG_SUCCESS = "success";
    private EditText inputName, inputEmail, inputPass, inputConfPass;
    private Button regisButton;
    public final Pattern emailPattern = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );
    Matcher matcherObj;

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
                String email = inputEmail.getText().toString().trim();
                String pass = inputPass.getText().toString();
                String confpass = inputConfPass.getText().toString();
                if (nama.isEmpty() && email.isEmpty() && pass.isEmpty() && confpass.isEmpty()){
                    inputName.setError("Required!");
                    inputEmail.setError("Required!");
                    inputPass.setError("Required!");
                    inputConfPass.setError("Required!");
                } else if(!isValidMail(email)) {
                    inputEmail.setError("Email Tidak Valid");
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
                            Toast.makeText(signUp.this, R.string.suc_regis, Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), signIn.class);
                            startActivity(i);
                        }

                        @Override
                        protected void onError(String message) {
                            super.onError(message);
                            Toast.makeText(signUp.this, R.string.fail_regis, Toast.LENGTH_SHORT).show();
//                            Toast.makeText(signUp.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                        }
                    });
                else
                    Toast.makeText(signUp.this, R.string.conf_pass, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean checkEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
