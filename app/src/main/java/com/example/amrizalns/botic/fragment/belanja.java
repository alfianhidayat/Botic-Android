package com.example.amrizalns.botic.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
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


public class belanja extends Fragment {

    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    ProgressDialog dialog;
    private List<itemObject> rowListItem = new ArrayList<>();
    private recyclerViewAdapter rcAdapter;

    public static belanja newInstance(String param1, String param2) {
        belanja fragment = new belanja();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_belanja, container, false);
        lLayout = new GridLayoutManager(view.getContext(), 2);
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        getShopping();
        return view;
    }

    private void getShopping() {
        RetrofitApi.getInstance().getApiService(SessionLogin.getAccessToken()).getShopping().enqueue(new PageCallback<List<ObjectItem>>(getActivity()) {
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
                    rowListItem.add(new itemObject(R.drawable.content_wisata2,
                            objectItem.getName(),
                            objectItem.getAddress(),
                            objectItem.getPrice(),
                            objectItem.getOpen(),
                            objectItem.getClose(),
                            objectItem.getDescription()));
                }
                rcAdapter.notifyDataSetChanged();
            }
        });
    }

    private List<itemObject> getAllItemList() {

        List<itemObject> allItems = new ArrayList<itemObject>();
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_hotel1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        return allItems;
    }

}
