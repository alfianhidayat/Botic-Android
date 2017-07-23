package com.bojonegorotic.amrizalns.botic.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bojonegorotic.amrizalns.botic.ImgViewPageAdapter;
import com.bojonegorotic.amrizalns.botic.activity.detail_content;
import com.bojonegorotic.amrizalns.botic.utils.CustomPicasso;
import com.botic.coreapps.AppsCore;
import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.Event;
import com.botic.coreapps.models.Picture;
import com.botic.coreapps.networks.RetrofitApi;
import com.bojonegorotic.amrizalns.botic.EventAdapter;
import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.activity.signIn;
import com.bojonegorotic.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.List;

public class event extends Fragment {
    View view;
    RecyclerView recyclerView;
    EventAdapter eventAdapter;
    List<Event> eventList = new ArrayList<>();
    ProgressDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_event, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_event);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        eventAdapter = new EventAdapter(getActivity(), eventList);
        recyclerView.setAdapter(eventAdapter);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        getEvent();
        return view;
    }

    private void getEvent() {
        RetrofitApi.getInstance(getActivity()).getApiService(SessionLogin.getAccessToken()).getEvent().enqueue(new PageCallback<List<Event>>(getActivity()) {
            @Override
            protected void onFinish() {
                dialog.dismiss();
            }

            @Override
            protected void onStart() {
                dialog.show();
            }

            @Override
            protected void onSuccess(List<Event> data) {
                super.onSuccess(data);
                eventList.clear();
                eventList.addAll(data);
                eventAdapter.notifyDataSetChanged();
            }

            @Override
            protected void onUnauthorized() {
                SessionLogin.reset();
                Intent intent = new Intent(getActivity(), signIn.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
                getActivity().startActivity(intent);
            }
        });
    }
}
