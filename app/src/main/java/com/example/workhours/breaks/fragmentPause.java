package com.example.workhours.breaks;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.content.ClipboardManager;

import com.example.workhours.R;
import com.example.workhours.ui.main.FragmentHelpingMethods;

import org.w3c.dom.Text;

public class fragmentPause extends FragmentHelpingMethods {

    private long pauseOut = 0;
    private String clipboardVal;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pause_zeiten, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        save();

        final EditText pause = view.findViewById(R.id.editTextTime5);
        final TextView ausgabe = view.findViewById(R.id.BreakAusgabe);

        final Button calcBreak = view.findViewById(R.id.button3);
        calcBreak.setOnClickListener(views -> {
            if(!isEmptyET(pause)){
                checkInput(pause);
                pauseOut =  getTime(pause.getText().toString(), true);
            }

            long end = IstZeit(pauseOut);
            long p = end - pauseOut;
            long actualTime = pauseOut-p;

            final TextView substr = view.findViewById(R.id.substract);
            final TextView added = view.findViewById(R.id.added);

            substr.setText(String.format("Pause abgezogen: %s" , parseTime(actualTime)));
            added.setText(String.format("Pause addiert: %s", parseTime(end)));
            ausgabe.setText(String.format("Eingegebene Zeit: %s \n Pause: %s min ", pause.getText(), p));

        });

        final TextView substr = view.findViewById(R.id.substract);
        substr.setOnLongClickListener(views -> {
            if (pauseOut != 0) {
                long end = IstZeit(pauseOut);
                long p = end - pauseOut;
                long actualTime = pauseOut - p;

                clipboardVal = parseTime(actualTime);
                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("substracted", clipboardVal);
                clipboard.setPrimaryClip(clip);

                makeToast("Pause: " + clipboardVal + " wurde ins Clipboard kopiert", 1);
            }
            return true;
        });

        final TextView added = view.findViewById(R.id.added);
        added.setOnLongClickListener(views -> {
                if (pauseOut != 0) {
                    clipboardVal = parseTime(pauseOut);
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("added", clipboardVal);
                    clipboard.setPrimaryClip(clip);

                    makeToast("Pause: " + clipboardVal + " wurde ins Clipboard kopiert", 1);
                }
                return true;
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
        final EditText pause = getView().findViewById(R.id.editTextTime5);
        pause.setText(mySPR.getString("pauseCalc","HH:mm"));
    }

    public void onStop(){
        super.onStop();

        SharedPreferences mySPR = this.getActivity().getSharedPreferences("MyFile",0);

        SharedPreferences.Editor editor = mySPR.edit();
        final EditText pause = getView().findViewById(R.id.editTextTime5);
        editor.putString("pauseCalc", pause.getText().toString());

        editor.commit();

    }

}