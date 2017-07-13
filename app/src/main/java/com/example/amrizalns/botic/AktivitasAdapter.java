package com.example.amrizalns.botic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.botic.coreapps.models.ObjectItem;
import com.example.amrizalns.botic.model.Aktivitas;

import java.util.List;

/**
 * Created by user on 10/07/2017.
 */

public class AktivitasAdapter extends RecyclerView.Adapter<AktivitasAdapter.MyViewHolder> {
    private List<ObjectItem> aktivitasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, location;
        public RatingBar rating;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_title_fav);
            location = (TextView) view.findViewById(R.id.tv_loc_fav);
            rating = (RatingBar) view.findViewById(R.id.rb_fav);
            image = (ImageView) view.findViewById(R.id.img_fav);
        }
    }


    public AktivitasAdapter(List<ObjectItem> aktivitasList) {
        this.aktivitasList = aktivitasList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.aktivitas_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ObjectItem aktivitas = aktivitasList.get(position);
        holder.title.setText(aktivitas.getName());
        holder.location.setText(aktivitas.getAddress());
        holder.rating.setRating(aktivitas.getRating());
    }

    @Override
    public int getItemCount() {
        return aktivitasList.size();
    }
}
