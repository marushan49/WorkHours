package com.example.workhours.breaks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.workhours.R;
import com.example.workhours.ui.main.FragmentHelpingMethods;

public class fragmentPause extends FragmentHelpingMethods {

    EditText firstVon,firstBis,firstPauseZeit,secondVon,secondBis,secondPauseZeit,thirdVon,thirdBis,thirdPauseZeit,fifthVon,fifthBis,fifthPauseZeit,fourthVon,fourthBis,fourthPauseZeit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pause_zeiten, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstVon  = view.findViewById(R.id.firstVon);
        firstBis  = view.findViewById(R.id.firstBis);
        firstPauseZeit  = view.findViewById(R.id.firstPauseZeit);
        secondVon  = view.findViewById(R.id.secondVon);
        secondBis  = view.findViewById(R.id.secondBis);
        secondPauseZeit  = view.findViewById(R.id.secondPauseZeit);
        thirdVon  = view.findViewById(R.id.thirdVon);
        thirdBis  = view.findViewById(R.id.firstBis);
        thirdPauseZeit  = view.findViewById(R.id.firstPauseZeit);
        fourthVon  = view.findViewById(R.id.firstVon);
        fourthBis  = view.findViewById(R.id.firstBis);
        fourthPauseZeit  = view.findViewById(R.id.firstPauseZeit);
        fifthVon  = view.findViewById(R.id.firstVon);
        fifthBis  = view.findViewById(R.id.firstBis);
        fifthPauseZeit  = view.findViewById(R.id.firstPauseZeit);

        final EditText bezeichung = view.findViewById(R.id.bezeichnung);
        bezeichung.setOnClickListener(views -> {
            if (bezeichung.getText().toString().equals("Bezeichnung")){
                bezeichung.getText().clear();
            }
        });

        final Button add = view.findViewById(R.id.add);
        add.setOnClickListener(views -> {

        });

        final Button delete = view.findViewById(R.id.delete);
        delete.setOnClickListener(views -> {

        });

    }
}