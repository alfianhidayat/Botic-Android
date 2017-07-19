package com.bojonegorotic.amrizalns.botic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alfianh on 1/31/17.
 */

public class SpinnerItemAdapter extends ArrayAdapter<String> {
    List<String> list = new ArrayList<>();
    protected LayoutInflater layoutInflater;
    Context context;

    public SpinnerItemAdapter(Context context, int resource, List<String> list) {
        super(context, resource);
        this.list = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.spinner_item_lis,
                    parent, false);
        }
        TextView tvName = (TextView) convertView
                .findViewById(R.id.tv_spinner_item);
        tvName.setText(list.get(position));
        return convertView;
    }

    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.spinner_dropdown,
                    parent, false);
        }
        TextView tvName = (TextView) convertView
                .findViewById(android.R.id.text1);
        tvName.setText(list.get(position));
        return convertView;
    }
}
