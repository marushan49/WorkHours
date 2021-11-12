package com.example.workhours.breaks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Break {
    private String von;
    private String bis;
    private String pause;
    private boolean mOnline;

    public Break(String von, String bis, String pause) {
        this.von = von;
        this.bis = bis;
        this.pause= pause;
    }

    public Break(JSONObject object){
        try {
            this.von = object.getString("von");
            this.bis = object.getString("bis");
            this.pause = object.getString("pause");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<Break> fromJson(JSONArray jsonObjects) {
        ArrayList<Break> breaks = new ArrayList<Break>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                breaks.add(new Break(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return breaks;
    }


    public String getBis() {
        return bis;
    }

    public String getPause() {
        return pause;
    }

    public String getVon() {
        return von;
    }
}