package com.bojonegorotic.amrizalns.botic.fragment;

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

import com.bojonegorotic.amrizalns.botic.R;
import com.bojonegorotic.amrizalns.botic.utils.Constants;
import com.bojonegorotic.amrizalns.botic.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class kesehatan extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_kesehatan, null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_kesehatan);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_kesehatan);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getFragmentManager());
        adapter.addFragment(Utils.getFragmentWithArgument(53, Constants.TAG_HEALTH), getString(R.string.rs));
        adapter.addFragment(Utils.getFragmentWithArgument(54, Constants.TAG_HEALTH), getString(R.string.klinik));

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
