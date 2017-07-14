package com.example.amrizalns.botic;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.botic.coreapps.AppsCore;
import com.botic.coreapps.models.Event;
import com.bumptech.glide.Glide;
import com.example.amrizalns.botic.utils.CustomPicasso;

import java.util.List;

/**
 * Created by alfianh on 7/14/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<Event> eventList;

    public EventAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        holder.tvDesc.setText(eventList.get(position).getDescription());
        holder.tvDate.setText(eventList.get(position).getTime());
        holder.tvName.setText(eventList.get(position).getName());
        CustomPicasso.getInstance(context).load(AppsCore.BASE_URL + "image/" + ((eventList.get(position).getPicture().size() == 0) ? R.mipmap.ic_botic : eventList.get(position).getPicture().get(0).getOriginalFilename()))
                .placeholder(R.mipmap.ic_botic)
                .error(R.mipmap.ic_botic)
                .into(holder.ivPhoto);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPhoto;
        TextView tvName, tvDate, tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.iv_photo);
            tvName = (TextView) itemView.findViewById(R.id.detail_name);
            tvDate = (TextView) itemView.findViewById(R.id.detail_date);
            tvDesc = (TextView) itemView.findViewById(R.id.detail_desc);
        }
    }
}
