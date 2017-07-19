package com.bojonegorotic.amrizalns.botic;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bojonegorotic.amrizalns.botic.model.CheckinData;

import java.util.List;

/**
 * Created by user on 12/07/2017.
 */

public class checkinAdapter extends RecyclerView.Adapter<checkinAdapter.MyViewHolder> {
    private List<CheckinData> mCheckinDatas;
    private int rowLayout;
    private Context mContext;
    private ItemClickListener clickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView nama, asal;

        public MyViewHolder(View view) {
            super(view);
            nama = (TextView) view.findViewById(R.id.txt_nama_checkin);
            asal = (TextView) view.findViewById(R.id.txt_asal_checkin);
            view.setTag(view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

    public checkinAdapter(List<CheckinData> checkinDatas, int rowLayout, Context context) {
        this.mCheckinDatas = checkinDatas;
        this.rowLayout =rowLayout;
        this.mContext = context;
    }


    @Override
    public checkinAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.checkindata_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CheckinData checkinData = mCheckinDatas.get(position);
        holder.nama.setText(checkinData.getNama());
        holder.asal.setText(checkinData.getAsal());
    }

    @Override
    public int getItemCount() {
        return mCheckinDatas.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
}
