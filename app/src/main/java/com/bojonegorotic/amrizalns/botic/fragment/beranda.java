package com.bojonegorotic.amrizalns.botic.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bojonegorotic.amrizalns.botic.viewPagerAdapter;

import com.bojonegorotic.amrizalns.botic.R;

import java.util.Timer;
import java.util.TimerTask;

public class beranda extends Fragment {

    View view;
    private int currentPage = 0;
    private Timer timer;
    ViewPager mViewPager;
    LinearLayout slider;
    private int imgcount;
    private ImageView[] img_content;
    viewPagerAdapter viewPagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_beranda, null);


        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        viewPagerAdapter vp = new viewPagerAdapter(getActivity());
        mViewPager.setAdapter(vp);

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 3) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1500, 3000);

        slider = (LinearLayout) view.findViewById(R.id.sliderContent);
        viewPagerAdapter = new viewPagerAdapter(getContext());
        mViewPager.setAdapter(viewPagerAdapter);
        slider();


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

    private void slider(){
        imgcount = viewPagerAdapter.getCount();
        img_content = new ImageView[imgcount];

        for (int i = 0; i < imgcount; i++){
            img_content[i] = new ImageView(getContext());
            img_content[i].setImageDrawable(getResources().getDrawable(R.drawable.nonactive_dots));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);
            slider.addView(img_content[i], params);
        }
        img_content[0].setImageDrawable(getResources().getDrawable(R.drawable.actived_dots));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < imgcount; i++){
                    img_content[i].setImageDrawable(getResources().getDrawable(R.drawable.nonactive_dots));
                }
                img_content[position].setImageDrawable(getResources().getDrawable(R.drawable.actived_dots));

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
