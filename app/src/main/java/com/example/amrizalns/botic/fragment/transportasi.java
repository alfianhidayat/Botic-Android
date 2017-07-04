package com.example.amrizalns.botic.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.itemObject;
import com.example.amrizalns.botic.recyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class transportasi extends Fragment {

    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;

    public transportasi() {
        // Required empty public constructor
    }

    public static transportasi newInstance(String param1, String param2) {
        transportasi fragment = new transportasi();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_transportasi, container, false);
        List<itemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(view.getContext(), 2);
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        recyclerViewAdapter rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);

        return view;
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
