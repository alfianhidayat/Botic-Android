package com.example.amrizalns.botic.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.networks.RetrofitApi;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.itemObject;
import com.example.amrizalns.botic.recyclerViewAdapter;
import com.example.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.List;

public class hotel extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    ProgressDialog dialog;
    private List<ObjectItem> rowListItem = new ArrayList<>();
    private recyclerViewAdapter rcAdapter;

    public hotel() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static hotel newInstance(String param1, String param2) {
        hotel fragment = new hotel();
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
        view = inflater.inflate(R.layout.fragment_hotel, container, false);
        lLayout = new GridLayoutManager(view.getContext(), 3);
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        getHotel();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getHotel();
    }

    private void getHotel() {
        RetrofitApi.getInstance(getActivity()).getApiService(SessionLogin.getAccessToken()).getHotel().enqueue(new PageCallback<List<ObjectItem>>(getActivity()) {
            @Override
            protected void onFinish() {
                dialog.dismiss();
            }

            @Override
            protected void onStart() {
                dialog.show();
            }

            @Override
            protected void onSuccess(List<ObjectItem> data) {
                super.onSuccess(data);
                rowListItem.clear();
                rowListItem.addAll(data);
                rcAdapter.notifyDataSetChanged();
            }
        });
    }

}
