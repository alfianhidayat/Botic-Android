package com.example.amrizalns.botic;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amrizalns.botic.activity.detail_content;

import java.util.List;

/**
 * Created by amrizalns on 6/7/17.
 */

public class recyclerViewAdapter extends RecyclerView.Adapter<recyclerViewHolder> {
    private List<itemObject> itemList;
    private Context context;

    public recyclerViewAdapter(Context context, List<itemObject> itemList) {
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
        holder.img.setImageResource(itemList.get(position).getImg());
        holder.loc.setText(itemList.get(position).getLocation());
        holder.name.setText(itemList.get(position).getName());
        holder.cost.setText(itemList.get(position).getCost());
        holder.time_open.setText(itemList.get(position).getTime_open());
        holder.time_close.setText(itemList.get(position).getTime_close());
        holder.desc.setText(itemList.get(position).getDesc());

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, detail_content.class);
                i.putExtra("img", itemList.get(position).getImg());
                i.putExtra("loc", itemList.get(position).getLocation());
                i.putExtra("name", itemList.get(position).getName());
                i.putExtra("cost", itemList.get(position).getCost());
                i.putExtra("time_open", itemList.get(position).getTime_open());
                i.putExtra("time_close", itemList.get(position).getTime_close());
                i.putExtra("desc", itemList.get(position).getDesc());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
