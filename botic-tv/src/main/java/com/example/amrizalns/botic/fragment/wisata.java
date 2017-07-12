package com.example.amrizalns.botic.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.IntentCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botic.coreapps.callbacks.PageCallback;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.networks.RetrofitApi;
import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.activity.signIn;
import com.example.amrizalns.botic.itemObject;
import com.example.amrizalns.botic.recyclerViewAdapter;
import com.example.amrizalns.botic.utils.SessionLogin;

import java.util.ArrayList;
import java.util.List;

public class wisata extends Fragment {

    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    ProgressDialog dialog;
    List<itemObject> rowListItem = new ArrayList<>();
    recyclerViewAdapter rcAdapter;

    public wisata() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment wisata.
     */
    // TODO: Rename and change types and number of parameters
    public static wisata newInstance(String param1, String param2) {
        wisata fragment = new wisata();
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
        view = inflater.inflate(R.layout.fragment_wisata, container, false);

//        rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(view.getContext(), 5);
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        getTourism();
        return view;
    }

    private void getTourism() {
        RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken()).getTourism().enqueue(new PageCallback<List<ObjectItem>>(getActivity()) {
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
                for (ObjectItem objectItem : data) {
                    rowListItem.add(new itemObject(R.mipmap.ic_botic,
                            objectItem.getName(),
                            objectItem.getAddress(),
                            objectItem.getPrice(),
                            objectItem.getOpen(),
                            objectItem.getClose(),
                            objectItem.getDescription()));
                }
                rcAdapter.notifyDataSetChanged();
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
