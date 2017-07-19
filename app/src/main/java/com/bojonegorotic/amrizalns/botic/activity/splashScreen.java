package com.bojonegorotic.amrizalns.botic.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                try{
                    sleep(3000);
                    if (SessionLogin.isExist()) {
                        Intent i = new Intent(getApplicationContext(), mainInterface.class);
                        startActivity(i);
                    }else{
                        Intent i = new Intent(getApplicationContext(), signIn.class);
                        startActivity(i);
                    }
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
