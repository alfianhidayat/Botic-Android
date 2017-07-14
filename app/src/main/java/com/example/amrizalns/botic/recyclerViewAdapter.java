package com.example.amrizalns.botic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.botic.coreapps.AppsCore;
import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.networks.RetrofitApi;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.example.amrizalns.botic.activity.detail_content;
import com.example.amrizalns.botic.utils.Constants;
import com.example.amrizalns.botic.utils.CustomPicasso;
import com.example.amrizalns.botic.utils.Utils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by amrizalns on 6/7/17.
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewHolder> {
    private List<ObjectItem> itemList;
    private Context context;

    public recyclerViewAdapter(Context context, List<ObjectItem> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public recyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_content, null);

        recyclerViewHolder rcv = new recyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(recyclerViewHolder holder, final int position) {
//        holder.img.setImageResource(R.drawable.ic_no_photo);
        CustomPicasso.getInstance(context).load(AppsCore.BASE_URL + "image/" + ((itemList.get(position).getPicture().size() == 0) ? R.drawable.ic_no_photo : itemList.get(position).getPicture().get(0).getOriginalFilename()))
                .placeholder(R.drawable.ic_no_photo)
                .error(R.drawable.ic_no_photo)
                .into(holder.img);
//        Glide.get(context).getRegistry()
//                .register(GlideUrl.class, InputStream.class, (ResourceTranscoder<GlideUrl, InputStream>) new OkHttpUrlLoader.Factory(Utils.builder(context)));
//        Glide.with(context).load(AppsCore.BASE_URL + "image/" + ((itemList.get(position).getPicture().size() == 0) ? R.mipmap.ic_botic : itemList.get(position).getPicture().get(0).getOriginalFilename())).into(holder.img);
        holder.loc.setText(itemList.get(position).getAddress());
        holder.name.setText(itemList.get(position).getName());
        holder.cost.setText(itemList.get(position).getPrice());
        holder.time_open.setText(itemList.get(position).getOpen());
        holder.time_close.setText(itemList.get(position).getClose());
        holder.desc.setText(itemList.get(position).getDescription());
        holder.ratingBar.setRating(itemList.get(position).getRating());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, detail_content.class);
                i.putExtra("object", itemList.get(position));
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
