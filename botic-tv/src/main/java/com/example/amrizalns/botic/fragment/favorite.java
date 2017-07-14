package com.example.amrizalns.botic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botic.coreapps.models.ObjectItem;
import com.example.amrizalns.botic.AktivitasAdapter;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.model.Aktivitas;

import java.util.ArrayList;
import java.util.List;

public class favorite extends Fragment {

    private List<ObjectItem> mAktivitasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AktivitasAdapter mAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_aktivitas);

        mAdapter = new AktivitasAdapter(mAktivitasList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareAktivitasData();
        return view;
    }

    private void prepareAktivitasData() {
        ObjectItem favorite = new ObjectItem();
        mAktivitasList.add(favorite);
    }
}