package com.example.kim.fishingdoc.fish;

import android.os.AsyncTask;

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
import java.util.HashMap;

/**
 * Created by user on 2016-07-15.
 */
class getFishTask extends AsyncTask<String, String, HashMap<String, ArrayList<String>>> {
    ArrayList<String> fishName = new ArrayList<String>();
    ArrayList<String> fishImg = new ArrayList<String>();

    static HashMap<String, ArrayList<String>> hash;

    @Override
    protected HashMap<String, ArrayList<String>> doInBackground(String... strings) {
        try {
            hash = new HashMap<String, ArrayList<String>>();
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

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject topLevel = jArray.getJSONObject(i);
                fishName.add(i, ObjToString(topLevel.get("name")));
                fishImg.add(i, ObjToString(topLevel.get("img_real")));
            }
            hash.put("fishName", fishName);
            hash.put("fishImg", fishImg);
//            Log.i("getFishTask result떴냐",""+hash);
            urlConnection.disconnect();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return hash;

    }

    @Override
    protected void onPostExecute(HashMap<String, ArrayList<String>> result) {
//        Log.i("hash떴냐..",""+result);
    }

    private String ObjToString(Object p)
    {
        String string = "";
        if (p == null)
        {
            string = "";
        }
        else
        {
            string = p.toString();
        }
        return string;
    }
}