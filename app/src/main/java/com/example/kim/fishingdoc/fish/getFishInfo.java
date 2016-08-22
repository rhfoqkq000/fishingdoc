package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 2016-07-15.
 */
class getFishInfo extends AsyncTask<String, String, ArrayList<String>> {
    String fish_id1, fish_id2, fish_id3, fish_id4, fish_id5 = "";
    ArrayList<String> fish_info = new ArrayList<String>();
    TextView distin, habit, live, bait, catched;
    int fish;

    public getFishInfo(String fish_id1, String fish_id2, String fish_id3, String fish_id4, String fish_id5,  TextView distin, TextView habit, TextView live, TextView bait, TextView catched,int fish){
        this.fish_id1 = fish_id1;
        this.fish_id2 = fish_id2;
        this.fish_id3 = fish_id3;
        this.fish_id4 = fish_id4;
        this.fish_id5 = fish_id5;
        this.distin = distin;
        this.habit = habit;
        this.live = live;
        this.bait = bait;
        this.catched = catched;
        this.fish = fish;
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        try {
            URL url = new URL(strings[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            JSONArray jArray = new JSONArray(builder.toString());

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject obj = jArray.getJSONObject(i);
                if(fish==Integer.parseInt(String.valueOf(obj.get("fish")))){
                    if(!String.valueOf(obj.get("distin")).equals("null")){
                        fish_info.add(i, String.valueOf(obj.get("distin")));
                    }
                    if(!String.valueOf(obj.get("habit")).equals("null")){
                        fish_info.add(i, String.valueOf(obj.get("habit")));
                    }
                    if(!String.valueOf(obj.get("live")).equals("null")){
                        fish_info.add(i, String.valueOf(obj.get("live")));
                    }
                    if(!String.valueOf(obj.get("bait")).equals("null")){
                        fish_info.add(i, String.valueOf(obj.get("bait")));
                    }
                    if(!String.valueOf(obj.get("catched")).equals("null")){
                        fish_info.add(i, String.valueOf(obj.get("catched")));
                    }

                }
            }

            Log.i("fish_info떴냐", ""+fish_info);
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return fish_info;

    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        Log.i("result떳냐",""+result);
        distin.setText(fish_info.get(0));
        habit.setText(fish_info.get(1));
        live.setText(fish_info.get(2));
        bait.setText(fish_info.get(3));
        catched.setText(fish_info.get(4));
    }

}