package com.example.amrizalns.botic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * Created by amrizalns on 7/5/17.
 */

public class item_content extends Fragment {
    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.item_content, null);
        List<itemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(view.getContext(), 2);
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.rv_itemcontent);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        recyclerViewAdapter rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);
        return view;
    }

    private List<itemObject> getAllItemList() {

        List<itemObject> allItems = new ArrayList<itemObject>();
        allItems.add(new itemObject(R.drawable.content_wisata1,
                "Aston City Conversation Hotel",
                "JL MH Thamrin Kab.Bojonegoro",
                "Rp.549.000,-",
                "00.01 am",
                "00.00 pm"
                , "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata2,
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
