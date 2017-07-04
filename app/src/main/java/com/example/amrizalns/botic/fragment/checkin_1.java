package com.example.amrizalns.botic.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.amrizalns.botic.R;

public class checkin_1 extends Fragment {
    View view;

    public checkin_1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment checkin_1.
     */
    // TODO: Rename and change types and number of parameters
    public static checkin_1 newInstance(String param1, String param2) {
        checkin_1 fragment = new checkin_1();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_checkin_1, container, false);

        Button daftar_pj = (Button) view.findViewById(R.id.btn_checkin_pj);
        daftar_pj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkin_2 check_2 = new checkin_2();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container,check_2);
                fragmentTransaction.commit();
            }
        });

        return  view;
    }

}
