package com.example.amrizalns.botic.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.helper.LanguageHelper;

import java.util.Locale;

public class language extends Fragment {
    private Locale myLocale;
    private Button btn_en, btn_id;
    LanguageHelper mLanguageHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_language, container, false);
        btn_en = (Button) view.findViewById(R.id.btn_en);
        btn_id = (Button) view.findViewById(R.id.btn_id);

        btn_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguageHelper.changeLocale(getResources(), "ID");
//                Toast.makeText(getContext(), "English", Toast.LENGTH_SHORT).show();
                change();
            }
        });
        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLanguageHelper.changeLocale(getResources(), "EN");
//                Toast.makeText(getContext(), "Indonesia", Toast.LENGTH_SHORT).show();
                change();
            }
        });
        return view;
    }

    private void change(){
        language language = new language();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_container, language);
        fragmentTransaction.commit();
    }

//    public void loadLocale()
//    {
//        String langPref = "Language";
//        SharedPreferences prefs = getContext().getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
//        String language = prefs.getString(langPref, "");
//        changeLang(language);
//    }
//
//    public void saveLocale(String lang)
//    {
//        String langPref = "Language";
//        SharedPreferences prefs = getContext().getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(langPref, lang);
//        editor.commit();
//    }
//
//    public void changeLang(String lang)
//    {
//        if (lang.equalsIgnoreCase(""))
//            return;
//        myLocale = new Locale(lang);
//        saveLocale(lang);
//        Locale.setDefault(myLocale);
//        android.content.res.Configuration config = new android.content.res.Configuration();
//        config.locale = myLocale;
//        getContext().getResources().updateConfiguration(config, getContext().getResources().getDisplayMetrics());
//    }
//
//    @Override
//    public void onConfigurationChanged(android.content.res.Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        if (myLocale != null){
//            newConfig.locale = myLocale;
//            Locale.setDefault(myLocale);
//            getContext().getResources().updateConfiguration(newConfig, getContext().getResources().getDisplayMetrics());
//        }
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_id:
//                mLanguageHelper.changeLocale(this.getResources(), "in");
//                break;
//            case R.id.btn_en:
//                mLanguageHelper.changeLocale(this.getResources(), "en");
//                break;
//            default:
//                break;
//        }
//    }
}
