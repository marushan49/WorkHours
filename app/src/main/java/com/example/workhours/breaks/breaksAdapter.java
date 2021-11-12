package com.example.workhours.breaks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.workhours.R;

import java.util.ArrayList;

public class breaksAdapter extends ArrayAdapter<Break> {

    public breaksAdapter(Context context, ArrayList<Break> breaks) {
        super(context, 0, breaks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Break _break = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_break, parent, false);
        }
        // Lookup view for data population
        TextView tvBreak = (TextView) convertView.findViewById(R.id.tvBreak);
        // Populate the data into the template view using the data object
        tvBreak.setText(_break.getVon() + " - " + _break.getBis() + " >>>> " + _break.getPause());
        // Return the completed view to render on screen

        return convertView;
    }
}