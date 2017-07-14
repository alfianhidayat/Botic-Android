package com.example.amrizalns.botic.fragment;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.CheckInParams;
import com.botic.coreapps.networks.RetrofitApi;
import com.example.amrizalns.botic.AktivitasAdapter;
import com.example.amrizalns.botic.ItemClickListener;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.activity.booking_gedung;
import com.example.amrizalns.botic.activity.mainInterface;
import com.example.amrizalns.botic.activity.signIn;
import com.example.amrizalns.botic.checkinAdapter;
import com.example.amrizalns.botic.model.CheckinData;
import com.example.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class checkin_2 extends Fragment implements View.OnClickListener, ItemClickListener {

    View view;
    private LinearLayout mLinearLayout;
    Button btnCheckInSubmit;
    private int i = 0;
    private ProgressDialog dialog;
    private List<CheckinData> mCheckinDataList = new ArrayList<>();
    private RecyclerView recyclerView;
    private checkinAdapter mCheckinAdapter;
    String nama, asal;
    List<CheckInParams.Visitor> visitors = new ArrayList<>();
    private int jmlTamu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_checkin_2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_checkin);

        mCheckinAdapter = new checkinAdapter(mCheckinDataList, R.layout.checkindata_list_row, getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mCheckinAdapter);

        btnCheckInSubmit = (Button) view.findViewById(R.id.btn_checkin_tamu);
        btnCheckInSubmit.setOnClickListener(this);
        mCheckinAdapter.setClickListener(this);

        String jumlah = getArguments().getString("daftar");
        jmlTamu = Integer.parseInt(jumlah);
        for (int i = 0; i < jmlTamu; i++) {
            CheckinData checkinData = new CheckinData("Isi Data Pengunjung - " + (i + 1), asal);
            mCheckinDataList.add(checkinData);
        }
        mCheckinAdapter.notifyDataSetChanged();

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_checkin_tamu:
                if (visitors.size() == jmlTamu) {
                    CheckInParams params = SessionLogin.getCheckIn();
                    params.setVisitors(visitors);
                    SessionLogin.saveCheckIn(params);
                    RetrofitApi.getInstance(getActivity()).getApiService(SessionLogin.getAccessToken())
                            .checkIn(SessionLogin.getCheckIn())
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
                } else {
                    Toast.makeText(getActivity(), "Inputkan semua data pengunjung", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onClick(final View view, final int position) {
        final CheckinData checkinData = mCheckinDataList.get(position);
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getContext());
        View mView = layoutInflaterAndroid.inflate(R.layout.input_data_tamu, null);
        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(getContext());
        alertDialogBuilderUserInput.setView(mView);
        final EditText namaInputDialogEditText = (EditText) mView.findViewById(R.id.namaInputDialog);
        final EditText asalInputDialogEditText = (EditText) mView.findViewById(R.id.asalInputDialog);
        final EditText usiaInputDialogEditText = (EditText) mView.findViewById(R.id.usiaInputDialog);
        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {
                        nama = namaInputDialogEditText.getText().toString();
                        asal = asalInputDialogEditText.getText().toString();
                        checkinData.setNama(nama);
                        checkinData.setAsal(asal);
                        checkinData.setUmur(usiaInputDialogEditText.getText().toString());
                        mCheckinAdapter.notifyDataSetChanged();
                        if (visitors.size() != jmlTamu)
                            visitors.add(new CheckInParams.Visitor(checkinData.getNama(), Integer.parseInt(checkinData.getUmur()), checkinData.getAsal()));
                        else
                            visitors.set(position, new CheckInParams.Visitor(checkinData.getNama(), Integer.parseInt(checkinData.getUmur()), checkinData.getAsal()));
                    }
                })
                .setNegativeButton("Batal",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        AlertDialog alertDialogAndroid = alertDialogBuilderUserInput.create();
        alertDialogAndroid.show();
        mCheckinAdapter.notifyDataSetChanged();
    }
}
