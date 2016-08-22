package com.example.kim.fishingdoc;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ryu on 2016-07-28.
 */

public class Json3 extends AsyncTask<String, String, HashMap<String, ArrayList<String>>> {
    public static ArrayList<String> date = new ArrayList<>();
    public static ArrayList<String> moonRise = new ArrayList<>();
    public static ArrayList<String> moonIng = new ArrayList<>();
    public static ArrayList<String> moonSet = new ArrayList<>();

    @Override
    protected HashMap<String, ArrayList<String>> doInBackground(String... params) {
        HashMap<String, ArrayList<String>> hash = new HashMap<String, ArrayList<String>>();
        StringBuilder jsonHtml = new StringBuilder();
        try {
            URL phpUrl = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) phpUrl.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);

                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        jsonHtml.append(line + "\n");
                    }
                    Log.i("jsonHtml.toString() 컴온", jsonHtml.toString());
                    br.close();
                }
                conn.disconnect(); //메모리누수방지

                try {
                    // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
                    JSONArray jArray = new JSONArray(jsonHtml.toString());
                    JSONObject jObject;
                    date.clear();
                    moonRise.clear();
                    moonIng.clear();
                    moonSet.clear();
                    for (int i = 0; i < jArray.length(); i++) {
                        jObject = jArray.getJSONObject(i);
                        date.add(i, jObject.getString("date"));
                        moonRise.add(i, jObject.getString("moonRise"));
                        moonIng.add(i, jObject.getString("moonIng"));
                        moonSet.add(i, jObject.getString("moonSet"));

                        hash.put("date", date);
                        hash.put("moonRise", moonRise);
                        hash.put("moonIng", moonIng);
                        hash.put("moonSet", moonSet);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }

    protected void onPostExecute(String str) {

    }


}