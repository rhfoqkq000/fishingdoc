package com.example.kim.fishingdoc.fish;


import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by user on 2016-07-15.
 */
class resetFish extends AsyncTask<String, String, String> {
    String id, distin, habit, live, bait, catched = "";
    int fish_id;
    ArrayList<String> idList = new ArrayList<String>();

    public resetFish(ArrayList<String> idList){
        this.id = id;
        this.fish_id = fish_id;
        this.distin = distin;
        this.habit = habit;
        this.live = live;
        this.bait = bait;
        this.catched = catched;
        this.idList = idList;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            for(int i = 0; i<idList.size(); i++){


            URL url = new URL(strings[0]+idList.get(i)+"/edit");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");
            JSONObject obj = new JSONObject();
            JSONObject obj2 = new JSONObject();

                obj.put("distin", "NULL");
                obj.put("habit", "NULL");
                obj.put("live", "NULL");
                obj.put("bait", "NULL");
                obj.put("catched", "NULL");

            obj2.put("doc", obj);
//            Log.i("obj2떴냐",""+obj2.toString());
//            Log.i("url뭐냐",""+url);
//            OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8");
//            osw.write(obj.toString());
//            osw.flush();
//            osw.close();
            OutputStreamWriter os = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            os.write(obj2.toString());
            os.flush();

            InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }

            urlConnection.disconnect();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    protected void onPostExecute(String result) {

    }

}