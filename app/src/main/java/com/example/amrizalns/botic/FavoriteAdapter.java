package com.example.amrizalns.botic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.botic.coreapps.models.ObjectItem;
import com.botic.coreapps.models.Picture;
import com.example.amrizalns.botic.activity.detail_content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 10/07/2017.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.MyViewHolder> {
    private List<com.example.amrizalns.botic.model.ObjectItem> aktivitasList;
    private List<Picture> mPictureList = new ArrayList<>();

    Context context;

    public FavoriteAdapter(Context context, List<com.example.amrizalns.botic.model.ObjectItem> aktivitasList) {
        this.aktivitasList = aktivitasList;
        this.context = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, location;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title_fav);
            location = (TextView) view.findViewById(R.id.tv_loc_fav);
            image = (ImageView) view.findViewById(R.id.img_fav);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.favorite_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final com.example.amrizalns.botic.model.ObjectItem aktivitas = aktivitasList.get(position);
        holder.title.setText(aktivitas.getName());
        holder.location.setText(aktivitas.getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, detail_content.class);
                ObjectItem object = new ObjectItem();
                object.setId(aktivitas.getId());
                object.setPhone(aktivitas.getPhone());
                object.setAddress(aktivitas.getAddress());
                object.setClose(aktivitas.getClose());
                object.setDescription(aktivitas.getDescription());
                object.setIdCategory(aktivitas.getIdCategory());
                object.setRating(aktivitas.getRating());
                object.setIdMenu(aktivitas.getIdMenu());
                object.setOpen(aktivitas.getOpen());
                object.setName(aktivitas.getName());
                i.putExtra("object", object);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return aktivitasList.size();
    }
}
