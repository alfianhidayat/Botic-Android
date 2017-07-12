package com.example.amrizalns.botic;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by amrizalns on 6/7/17.
 */

public class recyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView loc;
    public TextView name;
    public TextView desc;
    public TextView cost;
    public TextView time_open;
    public TextView time_close;
    public ImageView img;



    public LinearLayout linearLayout;

    public recyclerViewHolder(View itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.crd_content);

        itemView.setOnClickListener(this);
        loc = (TextView)itemView.findViewById(R.id.content_loc);
        name = (TextView) itemView.findViewById(R.id.content_title);
        img = (ImageView)itemView.findViewById(R.id.content_img);
        cost = (TextView) itemView.findViewById(R.id.content_cost);
        time_open = (TextView) itemView.findViewById(R.id.content_time_open);
        time_close = (TextView) itemView.findViewById(R.id.content_time_close);
        desc = (TextView) itemView.findViewById(R.id.content_desc);

    }

    @Override
    public void onClick(View v) {

    }
}
