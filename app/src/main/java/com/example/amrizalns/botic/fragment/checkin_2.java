package com.example.amrizalns.botic.fragment;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.networks.RetrofitApi;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.activity.booking_gedung;
import com.example.amrizalns.botic.activity.mainInterface;
import com.example.amrizalns.botic.activity.signIn;
import com.example.amrizalns.botic.utils.SessionLogin;


public class checkin_2 extends Fragment implements View.OnClickListener{

    View view;
    private LinearLayout mLinearLayout;
    Button btnCheckInSubmit;
    //    private EditText nama, usia, asal;
//    private TextView nmr;
    private int i = 0;
    private ProgressDialog dialog;

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
        btnCheckInSubmit = (Button) view.findViewById(R.id.btn_checkin_tamu);
        btnCheckInSubmit.setOnClickListener(this);
        String jumlah = getArguments().getString("daftar");
        int jmlTamu = Integer.parseInt(jumlah);
        for (i = 0; i < jmlTamu; i++) {
            FormTamu();
        }
        dialog= new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        return view;
    }

    private void FormTamu() {
        TextView nomer = new TextView(getActivity());
        nomer.setId(nomer.generateViewId());
        nomer.setText("Tamu " + (i + 1));
        nomer.setTextSize(14);
        nomer.setTextColor(getResources().getColor(R.color.colorContent));
        nomer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mLinearLayout.addView(nomer);

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


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_checkin_tamu:
                RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken())
                        .checin(SessionLogin.getCheckIn())
                        .enqueue(new PageCallback<Object>(getActivity()) {
                            @Override
                            protected void onStart() {
                                dialog.show();
                            }

                            @Override
                            protected void onFinish() {
                                dialog.dismiss();
                            }

                            @Override
                            protected void onSuccess(Object data) {
                                Toast.makeText(getActivity(), "Checkin Berhasil", Toast.LENGTH_SHORT).show();
                                SessionLogin.deleteCheckInParams();
                                Intent intent = new Intent(getActivity(), mainInterface.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }

                            @Override
                            protected void onError(String message) {
                                super.onError(message);
                                Toast.makeText(getActivity(), "Checkin Gagal", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            protected void onUnauthorized() {
                                SessionLogin.reset();
                                Intent intent = new Intent(getActivity(), signIn.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                            }
                        });
                break;
        }
    }
}
