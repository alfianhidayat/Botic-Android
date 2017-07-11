package com.example.botic_tv.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.botic_tv.R;
import com.example.botic_tv.utils.SessionLogin;

/**
 * Created by amrizalns on 7/11/17.
 */

public class splasScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
//        Thread t = new Thread(){
//            @Override
//            public void run() {
//                super.run();
//                try{
//                    sleep(3000);
//                    if (SessionLogin.isExist()) {
//                        Intent i = new Intent(getApplicationContext(), mainInterface.class);
//                        startActivity(i);
//                    }else{
//                        Intent i = new Intent(getApplicationContext(), signIn.class);
//                        startActivity(i);
//                    }
//                    finish();
//                }catch (InterruptedException e){
//                    e.printStackTrace();
//                }
//            }
//        };
//        t.start();
    }
}
