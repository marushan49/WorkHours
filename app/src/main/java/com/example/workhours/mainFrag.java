package com.example.workhours;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.workhours.ui.main.ExampleDialog;
import com.example.workhours.ui.main.FragmentHelpingMethods;

import java.nio.channels.SelectableChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.workhours.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class mainFrag extends FragmentHelpingMethods{

    private long mondayHours = 0;
    private long tuesdayHours = 0;
    private long wednesdayHours = 0;
    private long thursdayHours = 0;
    private long fridayHours = 0;
    private long p = 0;

    private final DateFormat timeParser = new SimpleDateFormat("HH:mm");

    private int weekhours = 0;

    private TextView summe;
    private EditText monday;
    private EditText tuesday;
    private EditText wednesday ;
    private EditText thursday;
    private EditText friday;
    private EditText hours;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        summe  = view.findViewById(R.id.summe);
        monday  = view.findViewById(R.id.monday);
        tuesday  = view.findViewById(R.id.tuesday);
        wednesday  = view.findViewById(R.id.wednesday);
        thursday  = view.findViewById(R.id.thursday);
        friday  = view.findViewById(R.id.friday);
        hours  = view.findViewById(R.id.hours);

        save();


        final CheckBox thursdayCH = view.findViewById(R.id.checkBox3);
        thursdayCH.setOnClickListener(views -> {
            if (thursdayCH.isChecked()) {
                thursday.setEnabled(false);
                thursday.setText("Wird berechnet...");
            }else {
                thursday.setEnabled(true);
                thursday.setText("0:00");
            }
        });

        final CheckBox fridayCH = view.findViewById(R.id.checkBox4);
        fridayCH.setOnClickListener(views -> {
            if (fridayCH.isChecked()) {
                friday.setEnabled(false);
                friday.setText("Wird berechnet...");
            }else{
                friday.setEnabled(true);
                friday.setText("0:00");
            }
        });

        final FloatingActionButton question = view.findViewById(R.id.floatingActionButton);
        question.setOnClickListener(
                views -> {
                    for (int i=0; i < 2; i++)
                    {
                        makeToast( "Hours: Format -> HH \nTage: Format -> HH:MM ", 3);
                    }
                });


        final Button berechnen = view.findViewById(R.id.button);
        berechnen.setOnClickListener(
                views -> {
                    if(!isEmptyET(monday)) {
                        checkInput(monday);
                        mondayHours = getTime(monday.getText().toString(), false);
                    }
                    if(!isEmptyET(tuesday)){
                        checkInput(tuesday);
                        tuesdayHours =  getTime(tuesday.getText().toString(), false);
                    }
                    if(!isEmptyET(wednesday)){
                        checkInput(wednesday);
                        wednesdayHours =  getTime(wednesday.getText().toString(), false);
                    }
                    if(!isEmptyET(thursday)){
                        checkInput(thursday);
                        thursdayHours =  getTime(thursday.getText().toString(), false);
                    }
                    if(!isEmptyET(friday)){
                        checkInput(friday);
                        fridayHours =  getTime(friday.getText().toString(), false);
                    }
                    if(!isEmptyET(hours)){
                        checkInput(hours);
                        weekhours =  getTime(hours.getText().toString(), true);
                    }

                    if(thursdayCH.isChecked() && fridayCH.isChecked()){
                        long minutes = mondayHours+tuesdayHours+wednesdayHours;
                        long l = weekhours - minutes;
                        if(minutes > weekhours){
                            ExampleDialog exmplDialog = new ExampleDialog("Fehler", "Summe: " + parseTime(minutes) + " der eingetragenen Stunden haben schon " + parseTime(weekhours) + " erreicht/überstiegen", "Ok");
                            exmplDialog.show(getActivity().getSupportFragmentManager(), "dialog");
                            return;
                        }
                        long end = l/2;
                        long pause = IstZeit(end);
                        p = pause - end;
                        thursday.setText(parseTime(pause));
                        friday.setText(parseTime(pause));
                        thursdayHours = pause;
                        fridayHours = pause;
                        thursday.setEnabled(false);
                        friday.setEnabled(false);
                    }
                    else if(!thursdayCH.isChecked() && fridayCH.isChecked()){
                        long mtwt = mondayHours+tuesdayHours+wednesdayHours+thursdayHours;
                        long weiter = weekhours - mtwt;
                        if(mtwt > weekhours){
                            ExampleDialog exmplDialog = new ExampleDialog("Fehler", "Summe: " + parseTime(mtwt) + " der eingetragenen Stunden haben schon " + parseTime(weekhours) + " erreicht/überstiegen", "Ok");
                            exmplDialog.show(getActivity().getSupportFragmentManager(), "dialog");
                            return;
                        }

                        long end = IstZeit(weiter);
                        p = end - weiter;
                        friday.setText(parseTime(end));
                        fridayHours = end;
                        friday.setEnabled(false);
                    }
                    else if(thursdayCH.isChecked() && !fridayCH.isChecked()){
                        long mtwt = mondayHours+tuesdayHours+wednesdayHours+fridayHours;
                        long weiter = weekhours - mtwt;

                        if(mtwt > weekhours){
                            ExampleDialog exmplDialog = new ExampleDialog("Fehler", "Summe: " + parseTime(mtwt) + " der eingetragenen Stunden haben schon " + parseTime(weekhours) + " erreicht/überstiegen", "Ok");
                            exmplDialog.show(getActivity().getSupportFragmentManager(), "dialog");
                            return;
                        }
                        long end = IstZeit(weiter);
                        p = end - weiter;
                        thursday.setText(parseTime(end));
                        thursdayHours = end;
                        thursday.setEnabled(false);
                    }

                    long sum = mondayHours+tuesdayHours+wednesdayHours+thursdayHours+fridayHours;

                    summe.setText(String.format("%s + %s + %s +  %s + %s \n= %s", parseTime(mondayHours), parseTime(tuesdayHours), parseTime(wednesdayHours),
                            parseTime(thursdayHours), parseTime(fridayHours), parseTime(sum)));

                    makeToast("Pause beträgt: " + p + " Minuten", 1);
                });
    }


    public long IstZeit(long sollzeit){
        long pause = 0;
        if (sollzeit <= 120) {
            pause = 0;
        } else if (sollzeit <= 134) {
            pause = sollzeit - 120;
        } else if (sollzeit <= 285) {
            pause = 15;
        } else if (sollzeit <= 299) {
            pause = sollzeit - 30;
        } else if (sollzeit <= 390) {
            pause = 30;
        } else if (sollzeit <= 404) {
            pause = sollzeit - 360;
        } else {
            pause = 45;
        }
        return sollzeit + pause;
    }

    private void save(){
        SharedPreferences mySPR = this.getActivity().getSharedPreferences("MyFile", 0);

        monday.setText(mySPR.getString("monday","HH:mm"));
        tuesday.setText(mySPR.getString("tuesday","HH:mm"));
        wednesday.setText(mySPR.getString("wednesday","HH:mm"));
        thursday.setText(mySPR.getString("thursday","HH:mm"));
        friday.setText(mySPR.getString("friday","HH:mm"));
        hours.setText(mySPR.getString("hours","HH:mm"));
    }

    public void onStop(){
        super.onStop();

        SharedPreferences mySPR = this.getActivity().getSharedPreferences("MyFile",0);

        SharedPreferences.Editor editor = mySPR.edit();

        editor.putString("hours", hours.getText().toString());
        editor.putString("monday", monday.getText().toString());
        editor.putString("tuesday", tuesday.getText().toString());
        editor.putString("wednesday", wednesday.getText().toString());
        editor.putString("thursday", thursday.getText().toString());
        editor.putString("friday", friday.getText().toString());

        editor.commit();

    }

}