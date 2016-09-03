package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;
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

/**
 * Created by user on 2016-07-15.
 */
class getHistory extends AsyncTask<String, String, String> {
    String history = "";
    int fish_id;
    TextView tvHistory;

    public getHistory(int fish_id, TextView tvHistory){
        this.fish_id = fish_id;
        this.tvHistory = tvHistory;
    }

    @Override
    protected String doInBackground(String... strings) {
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
//            Log.i("jArray뭐야",""+jArray);

                JSONObject topLevel = jArray.getJSONObject(fish_id);
                JSONObject topLevel2 = (JSONObject)topLevel.get("doc");
            history = topLevel2.getString("history").toString();


            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return history;

    }

    @Override
    protected void onPostExecute(String history) {
        tvHistory.setText(history);
    }

}