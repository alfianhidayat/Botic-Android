package com.example.amrizalns.botic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.amrizalns.botic.model.Aktivitas;

import java.util.List;

/**
 * Created by user on 10/07/2017.
 */

public class AktivitasAdapter extends RecyclerView.Adapter<AktivitasAdapter.MyViewHolder> {
    private List<Aktivitas> aktivitasList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, tgl;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_nama_ak);
            tgl = (TextView) view.findViewById(R.id.tv_tgl);
        }
    }


    public AktivitasAdapter(List<Aktivitas> aktivitasList) {
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
        Aktivitas aktivitas = aktivitasList.get(position);
        holder.title.setText(aktivitas.getNama());
        holder.tgl.setText(aktivitas.getTanggal());
    }

    @Override
    public int getItemCount() {
        return aktivitasList.size();
    }
}
