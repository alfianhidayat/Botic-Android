package com.bojonegorotic.amrizalns.botic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bojonegorotic.amrizalns.botic.utils.CustomPicasso;
import com.botic.coreapps.AppsCore;
import com.botic.coreapps.models.Event;

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
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvDesc.setText(eventList.get(position).getDescription());
        holder.tvDate.setText(eventList.get(position).getTime());
        holder.tvName.setText(eventList.get(position).getName());
        CustomPicasso.getInstance(context).load(AppsCore.BASE_URL + "image/" + ((eventList.get(position).getPicture().size() == 0) ? R.drawable.ic_no_photo : eventList.get(position).getPicture().get(0).getOriginalFilename()))
                .placeholder(R.drawable.ic_no_photo)
                .error(R.drawable.ic_no_photo)
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
