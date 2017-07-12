package com.example.amrizalns.botic.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amrizalns.botic.R;
import com.example.amrizalns.botic.utils.Constants;
import com.example.amrizalns.botic.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class tempatIbadah extends Fragment {

    ViewPager mViewPager;
    TabLayout mTabLayout;
    private GridLayoutManager lLayout;
    private RecyclerView.LayoutManager mLayoutManager;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tempat_ibadah, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_tempatibadah);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_tempatibadah);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager){
        belanja.Adapter adapter = new belanja.Adapter(getFragmentManager());
        adapter.addFragment(Utils.getFragmentWithArgument(39, Constants.TAG_PRAYING), "Masjid");
        adapter.addFragment(Utils.getFragmentWithArgument(41, Constants.TAG_PRAYING), "Gereja");
        adapter.addFragment(Utils.getFragmentWithArgument(44, Constants.TAG_PRAYING), "Vihara");
        adapter.addFragment(Utils.getFragmentWithArgument(43, Constants.TAG_PRAYING), "Pura");
        adapter.addFragment(Utils.getFragmentWithArgument(42, Constants.TAG_PRAYING), "Klenteng");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
