package com.example.workhours.breaks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workhours.R;
import com.example.workhours.ui.main.FragmentHelpingMethods;

import java.util.ArrayList;

public class fragmentPauseNew extends FragmentHelpingMethods {

    EditText firstVon,firstBis,firstPauseZeit;
    ArrayList<Break> breaks;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.break_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) view.findViewById(R.id.rvBreaks);

        // Create adapter passing in the sample user data
        breakAdapter adapter = new breakAdapter(breaks);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // That's all!

        firstVon  = view.findViewById(R.id.firstVon);
        firstBis  = view.findViewById(R.id.firstBis);
        firstPauseZeit  = view.findViewById(R.id.firstPauseZeit);


        final EditText bezeichung = view.findViewById(R.id.bezeichnung);
        bezeichung.setOnClickListener(views -> {
            if (bezeichung.getText().toString().equals("Bezeichnung")){
                bezeichung.getText().clear();
            }
        });

        final Button add = view.findViewById(R.id.add);
        add.setOnClickListener(views -> {

        });


    }
}