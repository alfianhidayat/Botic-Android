package com.example.amrizalns.botic.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.activity.booking_gedung;

public class booking extends Fragment {
    View view;
    public booking() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment booking.
     */
    // TODO: Rename and change types and number of parameters
    public static booking newInstance(String param1, String param2) {
        booking fragment = new booking();

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
        view = inflater.inflate(R.layout.fragment_booking, container, false);

        CardView crd_gedung = (CardView) view.findViewById(R.id.booking_gedung);
        crd_gedung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), booking_gedung.class);
                startActivity(i);
            }
        });

        CardView crd_kesenian = (CardView) view.findViewById(R.id.booking_kesenian);
        crd_kesenian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Menu belum tersedia", Toast.LENGTH_SHORT).show();

//                Intent i = new Intent(getActivity(), booking_kesenian.class);
//                startActivity(i);
            }
        });

        return view;
    }

}
