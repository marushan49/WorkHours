package com.example.workhours;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.workhours.ui.main.FragmentHelpingMethods;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class frag2Activity extends FragmentHelpingMethods {

    private long week1min = 0;
    private long week2min = 0;
    private long week3min = 0;
    private long week4min = 0;
    private EditText week1,week2, week3, week4,lohnEdit;
    private TextView sums;

    private final DateFormat timeParser = new SimpleDateFormat("HH:mm");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        week1 = view.findViewById(R.id.editTextTime);
        week2 = view.findViewById(R.id.editTextTime2);
        week3 = view.findViewById(R.id.editTextTime3);
        week4 = view.findViewById(R.id.editTextTime4);
        lohnEdit = view.findViewById(R.id.LohnEdit);
        sums = view.findViewById(R.id.textView102);

        save();

        final FloatingActionButton question = view.findViewById(R.id.floatingActionButton2);
        question.setOnClickListener(
                views -> {
                    for (int i=0; i < 2; i++)
                    {
                        messageBox("Stundenlohn: Format -> Zahl \nWochen (Kummuliert gearbeitete Stunden): Format -> HH:MM ");
                    }
                });

        final Button berechnen = view.findViewById(R.id.button2);
        berechnen.setOnClickListener(
                views -> {
                    if (!isEmptyET(week1)) {
                        checkInput(week1);
                        week1min = getTime(week1.getText().toString(), true);
                    }
                    if (!isEmptyET(week2)) {
                        checkInput(week2);
                        week2min = getTime(week2.getText().toString(), true);
                    }
                    if (!isEmptyET(week3)) {
                        checkInput(week3);
                        week3min = getTime(week3.getText().toString(), true);
                    }
                    if (!isEmptyET(week4)) {
                        checkInput(week4);
                        week4min = getTime(week4.getText().toString(), true);
                    }
                    double verdienst = 0;
                    long sum = week1min + week2min + week3min + week4min;
                    if(!isEmptyET(lohnEdit)) {
                        verdienst = ((double)sum / 60);
                    }

                    double lohn = Double.parseDouble(lohnEdit.getText().toString());
                    double test = round(lohn*verdienst, 2);

                    sums.setText(String.format("%s + %s + %s +  %s = %s \n Lohn: %s", mainFrag.parseTime(week1min), mainFrag.parseTime(week2min), mainFrag.parseTime(week3min), mainFrag.parseTime(week4min), mainFrag.parseTime(sum), test));
                });
    }

    private void save(){
        SharedPreferences mySPR = this.getActivity().getSharedPreferences("MyFile2", 0);
        week1.setText(mySPR.getString("week1", "HH:mm"));
        week2.setText(mySPR.getString("week2", "HH:mm"));
        week3.setText(mySPR.getString("week3", "HH:mm"));
        week4.setText(mySPR.getString("week4", "HH:mm"));
        lohnEdit.setText(mySPR.getString("lohn", "0"));
    }

    public void onStop() {
        super.onStop();

        SharedPreferences mySPR = this.getActivity().getSharedPreferences("MyFile2", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = mySPR.edit();

        editor.putString("week1", week1.getText().toString());
        editor.putString("week2", week2.getText().toString());
        editor.putString("week3", week3.getText().toString());
        editor.putString("week4", week4.getText().toString());
        editor.putString("lohn", lohnEdit.getText().toString());
        editor.commit();

    }

}
