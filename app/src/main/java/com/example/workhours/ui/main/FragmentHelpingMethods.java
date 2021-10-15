package com.example.workhours.ui.main;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import java.util.concurrent.TimeUnit;

public class FragmentHelpingMethods extends Fragment {

    @SuppressLint("DefaultLocale")
    public static String parseTime(long minutes) {
        return String.format("%02d:%02d",
                TimeUnit.MINUTES.toHours(minutes),
                TimeUnit.MINUTES.toMinutes(minutes) - TimeUnit.HOURS.toMinutes(
                        TimeUnit.MINUTES.toHours(minutes)));
    }

    public boolean isEmptyET(EditText et){
        return et.getText().toString().isEmpty() || et.getText().toString().equals("Wird berechnet...");
    }

    public int getTime(String hourFormat, boolean ausnahme) {
        int minutes = 0;
        String[] split = hourFormat.split(":");

        try {
            if(hourFormat.equals(""))
                return 0;

            if(Double.parseDouble(split[1]) >= 60 || Double.parseDouble(split[0]) < 0 || Double.parseDouble(split[1])  < 0 || (Double.parseDouble(split[0])  >= 24 && !ausnahme)){
                openFehlerDialog();
                return -1;
            }
            minutes += Double.parseDouble(split[0])*60;
            minutes += Double.parseDouble(split[1]);
            return minutes;
        } catch (Exception e) {
            return -1;
        }
    }

    public void checkInput(EditText s){
        if(!s.getText().toString().contains(":")){
            s.setText(appendItIf(s.getText().toString()));
        }
    }

    public String appendItIf(String s){
        StringBuilder text = new StringBuilder(s).append(":00");
        return text.toString();
    }

    public void openFehlerDialog(){
        ExampleDialog exmplDialog = new ExampleDialog("Fehler", "Eingabe ist ungültig!", "Abbrechen");
        exmplDialog.show(getActivity().getSupportFragmentManager(), "dialog");
    }

    public void makeToast(String text, int duration){
        for (int i=1; i <= duration; i++) {
          Toast.makeText(getActivity(),text, Toast.LENGTH_LONG).show();
        }
    }

    public double round(double value, int decimalPoints) {
        double d = Math.pow(10, decimalPoints);
        return Math.round(value * d) / d;
    }
}
