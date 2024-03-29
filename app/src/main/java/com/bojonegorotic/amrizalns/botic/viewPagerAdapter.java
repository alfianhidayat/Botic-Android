package com.bojonegorotic.amrizalns.botic;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by amrizalns on 6/1/17.
 */

public class viewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Integer[] img = {R.drawable.banner_boticapps_1, R.drawable.banner_boticapps_2, R.drawable.banner_boticapps_3};

    public viewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return img.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_beranda, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.img_vp);
        imageView.setImageResource(img[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;

        View view = (View) object;
        vp.removeView(view);
    }
}
