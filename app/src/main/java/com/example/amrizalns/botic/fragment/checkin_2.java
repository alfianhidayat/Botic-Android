package com.example.amrizalns.botic.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.amrizalns.botic.R;


public class checkin_2 extends Fragment {

    View view;
    private LinearLayout mLinearLayout;
    private EditText nama, usia, asal;

    public checkin_2() {
        // Required empty public constructor
    }

    public static checkin_2 newInstance(String param1, String param2) {
        checkin_2 fragment = new checkin_2();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkin_2, container, false);

        mLinearLayout = (LinearLayout) view.findViewById(R.id.checkin_tamu_form);
        String jumlah = getArguments().getString("daftar");
        int jmlTamu = Integer.parseInt(jumlah);
        for (int i = 0; i < jmlTamu; i++) {
            FormTamu();
        }
        return view;
    }

    private void FormTamu() {
        EditText nama = new EditText(getActivity());
        nama.setId(nama.generateViewId());
        nama.setHint("nama");
        nama.setTextSize(14);
        nama.setTextColor(getResources().getColor(R.color.colorContent));
        nama.setHintTextColor(getResources().getColor(R.color.colorContent));
        nama.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mLinearLayout.addView(nama);

        EditText usia = new EditText(getActivity());
        usia.setId(usia.generateViewId());
        usia.setHint("usia");
        usia.setTextSize(14);
        usia.setTextColor(getResources().getColor(R.color.colorContent));
        usia.setHintTextColor(getResources().getColor(R.color.colorContent));
        usia.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mLinearLayout.addView(usia);

        EditText asal = new EditText(getActivity());
        asal.setId(asal.generateViewId());
        asal.setHint("asal pengunjung");
        asal.setTextSize(14);
        asal.setTextColor(getResources().getColor(R.color.colorContent));
        asal.setHintTextColor(getResources().getColor(R.color.colorContent));
        asal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mLinearLayout.addView(asal);

        View view = new View(getContext());
        view.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, 3));
        view.setBackgroundColor(Color.parseColor("#000000"));
        mLinearLayout.addView(view);
    }
}
