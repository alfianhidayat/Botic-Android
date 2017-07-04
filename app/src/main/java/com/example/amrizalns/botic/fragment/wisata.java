package com.example.amrizalns.botic.fragment;

import android.content.Context;
import android.net.Uri;
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

public class wisata extends Fragment {

    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;

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

        List<itemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(view.getContext(), 2);
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        recyclerViewAdapter rcAdapter = new recyclerViewAdapter(view.getContext(), rowListItem);
        rView.setAdapter(rcAdapter);

        return view;
    }

    private List<itemObject> getAllItemList(){

        List<itemObject> allItems = new ArrayList<itemObject>();
        allItems.add(new itemObject(R.drawable.content_wisata1,
                "Negri Atas Angin",
                "Ds Sekar Kec.Bojonegoro",
                "Rp.10.000,-",
                "06.00 am",
                "00.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
                ));
        allItems.add(new itemObject(R.drawable.content_wisata2,
                "Kayangan Api",
                "Ds Ngasem Kec.Bojonegoro",
                "Rp.8.500,-",
                "09.00 am",
                "23.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata1,
                "Negri Atas Angin",
                "Ds Sekar Kec.Bojonegoro",
                "Rp.10.000,-",
                "06.00 am",
                "00.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata2,
                "Kayangan Api",
                "Ds Ngasem Kec.Bojonegoro",
                "Rp.8.500,-",
                "09.00 am",
                "23.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata1,
                "Negri Atas Angin",
                "Ds Sekar Kec.Bojonegoro",
                "Rp.10.000,-",
                "06.00 am",
                "00.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata2,
                "Kayangan Api",
                "Ds Ngasem Kec.Bojonegoro",
                "Rp.8.500,-",
                "09.00 am",
                "23.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata1,
                "Negri Atas Angin",
                "Ds Sekar Kec.Bojonegoro",
                "Rp.10.000,-",
                "06.00 am",
                "00.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        allItems.add(new itemObject(R.drawable.content_wisata2,
                "Negri Atas Angin",
                "Ds Sekar Kec.Bojonegoro",
                "Rp.10.000,-",
                "06.00 am",
                "00.00 pm"
                ,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
        ));
        return allItems;
    }

}
