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

import java.util.ArrayList;
import java.util.List;


public class belanja extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_belanja, null);

        mViewPager = (ViewPager) view.findViewById(R.id.vp_belanja);
        setupViewPager(mViewPager);

        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout_belanja);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getFragmentManager());
        Bundle bundleSwalayan = new Bundle();
        item_content fragmentSwalayan = new item_content();
        bundleSwalayan.putInt(Constants.TAG_ID_CATEGORY, 33);
        bundleSwalayan.putString(Constants.TAG_OBJECT_TYPE, Constants.TAG_SHOPPING);
        fragmentSwalayan.setArguments(bundleSwalayan);
        adapter.addFragment(fragmentSwalayan, "Mall & Swalayan");
        item_content fragmentMinimarket = new item_content();
        Bundle bundleMinimarket = new Bundle();
        bundleMinimarket.putInt(Constants.TAG_ID_CATEGORY, 36);
        bundleMinimarket.putString(Constants.TAG_OBJECT_TYPE, Constants.TAG_SHOPPING);
        fragmentMinimarket.setArguments(bundleMinimarket);
        adapter.addFragment(fragmentMinimarket, "Minimarket");
        item_content fragmentPasar = new item_content();
        Bundle bundlePasar = new Bundle();
        bundlePasar.putInt(Constants.TAG_ID_CATEGORY, 37);
        bundlePasar.putString(Constants.TAG_OBJECT_TYPE, Constants.TAG_SHOPPING);
        fragmentPasar.setArguments(bundlePasar);
        adapter.addFragment(fragmentPasar, getString(R.string.pasar));
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
