package com.bojonegorotic.amrizalns.botic.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bojonegorotic.amrizalns.botic.R;

import java.util.Timer;

public class beranda extends Fragment {

    View view;
    private int currentPage = 0;
    private Timer timer;

    public beranda() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment beranda.
     */
    // TODO: Rename and change types and number of parameters
    public static beranda newInstance(String param1, String param2) {
        beranda fragment = new beranda();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        view = inflater.inflate(R.layout.fragment_beranda, null);

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        ImageView wisata = (ImageView) view.findViewById(R.id.menu_wisata);
        wisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wisata wisata = new wisata();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, wisata);
                fragmentTransaction.commit();
//                Toast.makeText(getContext(), "Clicked ", Toast.LENGTH_LONG).show();
            }
        });

        ImageView hotel = (ImageView) view.findViewById(R.id.menu_hotel);
        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotel hotel = new hotel();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, hotel);
                fragmentTransaction.commit();
            }
        });

        ImageView belanja = (ImageView) view.findViewById(R.id.menu_belanja);
        belanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                belanja belanja = new belanja();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, belanja);
                fragmentTransaction.commit();
            }
        });

        ImageView transportasi = (ImageView) view.findViewById(R.id.menu_transportasi);
        transportasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transportasi t = new transportasi();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, t);
                fragmentTransaction.commit();
            }
        });

        ImageView checkin = (ImageView) view.findViewById(R.id.menu_checkin);
        checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkin_1 check_1 = new checkin_1();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, check_1);
                fragmentTransaction.commit();
            }
        });

        ImageView kuliner = (ImageView) view.findViewById(R.id.menu_kuliner);
        kuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kuliner kuliner = new kuliner();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, kuliner);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
