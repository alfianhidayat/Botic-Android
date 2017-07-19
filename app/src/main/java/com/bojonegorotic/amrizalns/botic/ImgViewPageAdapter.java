package com.bojonegorotic.amrizalns.botic;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.botic.coreapps.AppsCore;
import com.botic.coreapps.models.Picture;
import com.bojonegorotic.amrizalns.botic.utils.CustomPicasso;

import java.util.List;

/**
 * Created by user on 14/07/2017.
 */

public class ImgViewPageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mResources;
    private List<Picture> pictureList;

    public ImgViewPageAdapter(Context mContext, int[] mResources) {
        this.mContext = mContext;
        this.mResources = mResources;
    }

    public void setPictureList(List<Picture> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public int getCount() {
        return pictureList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.pager_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.img_pager_item);
//        imageView.setImageResource(mResources[position]);
        CustomPicasso.getInstance(mContext).load(AppsCore.BASE_URL + "image/" + ((pictureList.size() == 0) ? R.drawable.ic_no_photo : pictureList.get(position).getOriginalFilename()))
                .placeholder(R.drawable.ic_no_photo)
                .error(R.drawable.ic_no_photo)
                .into(imageView);

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
