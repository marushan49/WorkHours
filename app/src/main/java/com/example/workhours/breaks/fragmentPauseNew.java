package com.example.workhours.breaks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.workhours.R;
import com.example.workhours.ui.main.FragmentHelpingMethods;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.ArrayList;

public class fragmentPauseNew extends FragmentHelpingMethods {

    ArrayList<Break> arrayOfBreaks = new ArrayList<Break>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.break_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        breaksAdapter adapter = new breaksAdapter(this.getContext(), arrayOfBreaks);
        ListView lvContacts = (ListView) view.findViewById(R.id.lvBreaks);
        lvContacts.setAdapter(adapter);

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