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

/**
 * Created by Ryu on 2016-07-28.
 */

public class Json3 extends AsyncTask<String, String, String> {
    public static ArrayList<String> date = new ArrayList<>();
    public static ArrayList<String> moonRise = new ArrayList<>();
    public static ArrayList<String> moonIng = new ArrayList<>();
    public static ArrayList<String> moonSet = new ArrayList<>();

    @Override
    protected String doInBackground(String... params) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonHtml.toString();
    }

    protected void onPostExecute(String str) {
        try {
            // PHP에서 받아온 JSON 데이터를 JSON오브젝트로 변환
            JSONArray jArray = new JSONArray(str);
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
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getRise(String day) {
        int num = 0;
        for (int i = 0; i < date.size(); i++) {
            if (day.equals(date.get(i))) {
                num = i;
            }
        }
        return moonRise.get(num);
    }

    public String getIng(String day) {
        int num = 0;
        for (int i = 0; i < date.size(); i++) {
            if (day.equals(date.get(i))) {
                num = i;
            }
        }
        return moonIng.get(num);
    }

    public String getSet(String day) {
        int num = 0;
        for (int i = 0; i < date.size(); i++) {
            if (day.equals(date.get(i))) {
                num = i;
            }
        }
        return moonSet.get(num);
    }
}